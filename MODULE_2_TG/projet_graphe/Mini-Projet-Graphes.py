import subprocess
import sys



required_packages = ["networkx", "matplotlib", "fpdf"]

def installer_packages(packages):
    for package in packages:
        try:
            __import__(package)
        except ImportError:
            print(f"[INFO] {package} non trouvé. Installation en cours...")
            subprocess.check_call([sys.executable, "-m", "pip", "install", package])
            print(f"[OK] {package} installé.")

installer_packages(required_packages)
# ---------------------- FIN INSTALLATION ----------------------

import os
import tkinter as tk
from tkinter import filedialog, messagebox, scrolledtext, simpledialog
import networkx as nx
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import datetime
import random
import csv
import sqlite3
import hashlib
from fpdf import FPDF

# --- 1.  ---



# --- 1. INITIALISATION BASE DE DONNÉES ---
def init_db():
    conn = sqlite3.connect("users_labo.db")
    cursor = conn.cursor()
    
    # --- Table Users ---
    cursor.execute('''CREATE TABLE IF NOT EXISTS users 
                      (id INTEGER PRIMARY KEY AUTOINCREMENT, 
                       username TEXT UNIQUE, 
                       email TEXT, 
                       password TEXT)''')
    
    # --- Table History ---
    # Création initiale si elle n'existe pas
    cursor.execute('''CREATE TABLE IF NOT EXISTS history
                      (id INTEGER PRIMARY KEY AUTOINCREMENT,
                       username TEXT,
                       titre TEXT,
                       contenu TEXT,
                       image TEXT,
                       date TEXT)''')
    
    # Vérifier si toutes les colonnes existent, sinon les ajouter (utile si DB ancienne)
    cursor.execute("PRAGMA table_info(history)")
    colonnes = [c[1] for c in cursor.fetchall()]
    
    colonnes_attendues = ['username', 'titre', 'contenu', 'image', 'date']
    for col in colonnes_attendues:
        if col not in colonnes:
            cursor.execute(f"ALTER TABLE history ADD COLUMN {col} TEXT")
    
    conn.commit()
    conn.close()


# --- 2. SYSTÈME D'AUTHENTIFICATION ---
class AuthManager:
    def __init__(self, root, login_success_callback):
        self.root = root
        self.callback = login_success_callback
        self.show_login()

    def hash_pw(self, pw):
        return hashlib.sha256(pw.encode()).hexdigest()

    def clear_screen(self):
        for widget in self.root.winfo_children():
            widget.destroy()

    def show_login(self):
        self.clear_screen()
        self.root.title("Connexion - MST-SIDI-2025")
        self.root.geometry("400x500")
        self.root.configure(bg="#2c3e50")

        frame = tk.Frame(self.root, bg="#34495e", padx=20, pady=20)
        frame.place(relx=0.5, rely=0.5, anchor="center")

        tk.Label(frame, text="MINI PROJET THEORIE DES GRAPHES", fg="#f1c40f", bg="#34495e", font=('Arial', 14, 'bold')).pack(pady=10)
        
        tk.Label(frame, text="Utilisateur:", fg="white", bg="#34495e").pack()
        self.entry_u = tk.Entry(frame); self.entry_u.pack(pady=5)
        
        tk.Label(frame, text="Mot de passe:", fg="white", bg="#34495e").pack()
        self.entry_p = tk.Entry(frame, show="*"); self.entry_p.pack(pady=5)

        tk.Button(frame, text="Se connecter", bg="#27ae60", fg="white", command=self.login).pack(pady=10, fill="x")
        tk.Button(frame, text="Créer un compte", bg="#2980b9", fg="white", command=self.show_register).pack(fill="x")

    def show_register(self):
        self.clear_screen()
        frame = tk.Frame(self.root, bg="#34495e", padx=20, pady=20)
        frame.place(relx=0.5, rely=0.5, anchor="center")

        tk.Label(frame, text="INSCRIPTION", fg="#f1c40f", bg="#34495e", font=('Arial', 14, 'bold')).pack(pady=10)
        
        fields = ["Nom", "Email", "Mot de passe"]
        self.reg_entries = {}
        for f in fields:
            tk.Label(frame, text=f, fg="white", bg="#34495e").pack()
            e = tk.Entry(frame, show="*" if f == "Mot de passe" else "")
            e.pack(pady=5)
            self.reg_entries[f] = e

        tk.Button(frame, text="Valider", bg="#e67e22", fg="white", command=self.register).pack(pady=10, fill="x")
        tk.Button(frame, text="Retour", bg="#7f8c8d", fg="white", command=self.show_login).pack(fill="x")

    def register(self):
        u = self.reg_entries["Nom"].get()
        m = self.reg_entries["Email"].get()
        p = self.hash_pw(self.reg_entries["Mot de passe"].get())
        
        try:
            conn = sqlite3.connect("users_labo.db")
            conn.execute("INSERT INTO users (username, email, password) VALUES (?,?,?)", (u, m, p))
            conn.commit(); conn.close()
            messagebox.showinfo("Succès", "Compte créé !")
            self.show_login()
        except: messagebox.showerror("Erreur", "Utilisateur déjà existant")

    def login(self):
        u = self.entry_u.get()
        p = self.hash_pw(self.entry_p.get())
        conn = sqlite3.connect("users_labo.db")
        res = conn.execute("SELECT * FROM users WHERE username=? AND password=?", (u, p)).fetchone()
        conn.close()
        if res: self.callback(u)
        else: messagebox.showerror("Erreur", "Identifiants invalides")

# --- 3. CLASSE GraphApp ---
class GraphApp:
    def __init__(self, root, user_name="BAHIDA YOUSSEF"):
        self.root = root
        self.user_name = user_name
        self.root.title(f"Théorie des Graphes - MST-SIDI-2025 - ({self.user_name})")
        self.root.geometry("1500x950")
        
        self.graph = nx.Graph()
        self.pos = {}
        self.palette = ['#e74c3c', '#2ecc71', '#3498db', '#f1c40f', '#9b59b6', '#e67e22', '#1abc9c', '#7f8c8d', '#d35400', '#27ae60']
        self.history_data = []
        if not os.path.exists("temp_plots"): os.makedirs("temp_plots")

        self.fond_sombre = "#2c3e50"
        self.fond_clair = "#ecf0f1"

        # --- HEADER AVEC NOM UTILISATEUR ---
        self.header_frame = tk.Frame(root, bg="#2f00ff", height=60)
        self.header_frame.pack(side="top", fill="x")

        self.label_user = tk.Label(self.header_frame, 
                                text="BAHIDA YOUSSEF --- MST-SIDI-2025",
                                fg="#ff0000", bg="#ffffff", font=('Courier', 12, 'bold'))
        self.label_user.pack(pady=(6,0))

        self.label_username = tk.Label(self.header_frame,
                                    text=f"{self.user_name.upper()} (connecté)",
                                    fg="white", bg="#1a252f", font=('Arial', 10, 'bold'))
        self.label_username.pack()

        # 1. PIED DE PAGE
        self.footer = tk.Frame(root, bg="#1a252f", height=40)
        self.footer.pack(side="bottom", fill="x")
        self.signature = tk.Label(self.footer, text=f"----  {self.user_name.upper()} -- MST-SIDI-2025  ----", 
                                  fg="#f1c40f", bg="#1a252f", font=('Courier', 12, 'bold'))
        self.signature.pack(expand=True)

        # 2. BARRE LATÉRALE GAUCHE
        self.sidebar_gauche = tk.Frame(root, width=320, bg=self.fond_sombre)
        self.sidebar_gauche.pack(side="left", fill="y")
        
        self.zone_fichiers = tk.LabelFrame(self.sidebar_gauche, text="Fichiers & Données", bg="#34495e", fg="yellow", padx=5, pady=5)
        self.zone_fichiers.pack(fill="x", padx=10, pady=5)
        
        tk.Button(self.zone_fichiers, text="📥 Importer CSV", command=self.importer_graphe, bg="#3498db", fg="white").pack(fill="x", pady=1)
        tk.Button(self.zone_fichiers, text="📝 Générer Template", command=self.generer_templates, bg="#8e44ad", fg="white").pack(fill="x", pady=1)
        tk.Button(self.zone_fichiers, text="📄 Exporter Rapport PDF", command=self.exporter_tout, bg="#27ae60", fg="white").pack(fill="x", pady=1)
        tk.Button(self.zone_fichiers, text="🎲 Graphe Aléatoire", command=self.generer_aleatoire, bg="#d35400", fg="white").pack(fill="x", pady=1)

        self.zone_edition = tk.LabelFrame(self.sidebar_gauche, text="Édition du Graphe", bg="#34495e", fg="white", padx=5, pady=5)
        self.zone_edition.pack(fill="x", padx=10, pady=5)
        
        self.entree_noeud = self.creer_champ(self.zone_edition, "Nom Sommet :")
        tk.Button(self.zone_edition, text="➕ Ajouter Sommet", command=self.ajouter_noeud_manuel).pack(fill="x", pady=2)
        
        self.arete_u = self.creer_champ(self.zone_edition, "De (U) :")
        self.arete_v = self.creer_champ(self.zone_edition, "Vers (V) :")
        self.arete_w = self.creer_champ(self.zone_edition, "Poids :")
        self.arete_w.insert(0, "1")
        tk.Button(self.zone_edition, text="🔗 Connecter", command=self.ajouter_arete_manuelle, bg="#2980b9", fg="white").pack(fill="x", pady=2)

        tk.Label(self.zone_edition, text="Suppression", fg="#e74c3c", bg="#34495e", font=('Arial', 8, 'bold')).pack(pady=2)
        tk.Button(self.zone_edition, text="❌ Supprimer Sommet", command=self.supprimer_noeud, bg="#c0392b", fg="white").pack(fill="x", pady=1)
        tk.Button(self.zone_edition, text="✂ Supprimer Arête", command=self.supprimer_arete, bg="#c0392b", fg="white").pack(fill="x", pady=1)

        self.zone_algos = tk.LabelFrame(self.sidebar_gauche, text="Algorithmes", bg="#34495e", fg="#FFFFFF", padx=5, pady=5)
        self.zone_algos.pack(fill="x", padx=10, pady=5)
        self.creer_boutons_algos()
        
        tk.Button(self.sidebar_gauche, text="🔄 Réinitialiser Visuel", command=self.reset_visuel, bg="#16a085", fg="white").pack(fill="x", padx=20, pady=10)
        tk.Button(self.sidebar_gauche, text="🔒 Déconnexion", command=self.logout, bg="#c0392b", fg="white",
                  font=('Arial', 10, 'bold')).pack(fill="x", padx=20, pady=5)

        # 3. BARRE LATÉRALE DROITE
        self.sidebar_droite = tk.Frame(root, width=400, bg=self.fond_clair)
        self.sidebar_droite.pack(side="right", fill="y")

        # Onglets Traitement / Archives
        self.tab_control = tk.Frame(self.sidebar_droite, bg=self.fond_clair)
        self.tab_control.pack(fill="both", expand=True, padx=5, pady=5)

        # Boutons pour changer de vue
        self.current_view = "traitement"
        btn_frame = tk.Frame(self.sidebar_droite, bg=self.fond_clair)
        btn_frame.pack(fill="x")
        tk.Button(btn_frame, text="🟢 Traitement en cours", command=lambda: self.changer_vue("traitement")).pack(side="left", fill="x", expand=True)
        tk.Button(btn_frame, text="📜 Archives", command=lambda: self.changer_vue("archives")).pack(side="left", fill="x", expand=True)

        # Zone Traitement
        self.zone_traitement = scrolledtext.ScrolledText(self.tab_control, width=45, font=('Consolas', 9))
        self.zone_traitement.pack(fill="both", expand=True)

        # Zone Archives
        self.zone_archives = scrolledtext.ScrolledText(self.tab_control, width=45, font=('Consolas', 9))
        self.zone_archives.pack(fill="both", expand=True)
        self.zone_archives.pack_forget()

        # Bouton Sauvegarder
        tk.Button(self.sidebar_droite, text="💾 Sauvegarder Historique", command=self.sauvegarder_resultat, bg="#27ae60", fg="white").pack(fill="x", padx=5, pady=5)
        tk.Button(self.sidebar_droite, text=" Effacer Historique", command=self.clear_results, bg="#e74c3c", fg="white").pack(fill="x", padx=5, pady=5)

        # 4. ZONE VISUALISATION
        self.cadre_principal = tk.Frame(root, bg="white")
        self.cadre_principal.pack(side="left", fill="both", expand=True)
        self.fig, self.ax = plt.subplots(figsize=(8, 6))
        self.canvas = FigureCanvasTkAgg(self.fig, master=self.cadre_principal)
        self.canvas.get_tk_widget().pack(fill="both", expand=True)

        #Exporter par date l'historique
        tk.Button(self.sidebar_droite, text="📅 Exporter Archives par Date", 
          command=self.exporter_par_date, bg="#2980b9", fg="white").pack(fill="x", padx=5, pady=5)


    # ---------------- FONCTIONS ----------------
    def creer_champ(self, parent, texte_label):
        tk.Label(parent, text=texte_label, fg="white", bg="#34495e", font=('Arial', 8)).pack()
        ent = tk.Entry(parent, width=15); ent.pack(pady=1); return ent

    def capturer_image(self, nom):
        path = f"temp_plots/plot_{nom}_{len(self.history_data)}.png"
        self.fig.savefig(path, bbox_inches='tight'); return path

    def journaliser(self, titre, contenu):
        horaire = datetime.datetime.now().strftime('%H:%M:%S')
        format_text = f"\n[{horaire}] {titre.upper()}\n{contenu}\n{'-'*35}\n"
        self.zone_traitement.insert(tk.END, format_text)
        self.zone_traitement.see(tk.END)
        img_path = self.capturer_image(titre.replace(" ", "_"))
        self.history_data.append({'titre': titre, 'texte': contenu, 'image': img_path, 'heure': horaire})

    def changer_vue(self, vue):
        if vue == "traitement":
            self.zone_archives.pack_forget()
            self.zone_traitement.pack(fill="both", expand=True)
        else:
            self.zone_traitement.pack_forget()
            self.zone_archives.pack(fill="both", expand=True)
            self.charger_archives()
        self.current_view = vue

    def sauvegarder_resultat(self):
        if not self.history_data: return messagebox.showwarning("Vide", "Aucun résultat à sauvegarder.")
        conn = sqlite3.connect("users_labo.db")
        for item in self.history_data:
            date_str = datetime.datetime.now().strftime('%Y-%m-%d_%H-%M-%S')
            conn.execute("INSERT INTO history (username, titre, contenu, image, date) VALUES (?,?,?,?,?)",
                         (self.user_name, item['titre'], item['texte'], item['image'], date_str))
        conn.commit()
        conn.close()
        messagebox.showinfo("Sauvegarde", "Historique sauvegardé avec succès !")

    def charger_archives(self):
        self.zone_archives.delete('1.0', tk.END)
        conn = sqlite3.connect("users_labo.db")
        rows = conn.execute("SELECT date, titre, contenu FROM history WHERE username=? ORDER BY id DESC", (self.user_name,)).fetchall()
        conn.close()
        for date_str, titre, contenu in rows:
            self.zone_archives.insert(tk.END, f"[{date_str}] {titre}\n{contenu}\n{'-'*40}\n")

    # ---------------- GRAPHE ----------------
    def choisir_sommet(self, msg="Choisir un sommet :"):
        nodes = list(self.graph.nodes())
        if not nodes: return None
        res = simpledialog.askstring("Sélection", f"{msg}\nSommets: {', '.join(nodes)}")
        return res if res in nodes else None

    def dessiner_graphe(self, highlight_edges=None, node_colors=None):
        self.ax.clear()
        if not self.graph.nodes: self.canvas.draw(); return
        c_list = [node_colors.get(n, 'skyblue') for n in self.graph.nodes()] if node_colors else 'skyblue'
        nx.draw_networkx_nodes(self.graph, self.pos, node_color=c_list, node_size=800, ax=self.ax)
        nx.draw_networkx_labels(self.graph, self.pos, font_weight='bold', ax=self.ax)
        nx.draw_networkx_edges(self.graph, self.pos, edge_color='gray', width=1, ax=self.ax)
        if highlight_edges:
            nx.draw_networkx_edges(self.graph, self.pos, edgelist=highlight_edges, edge_color='red', width=3, ax=self.ax)
        labels = nx.get_edge_attributes(self.graph, 'weight')
        nx.draw_networkx_edge_labels(self.graph, self.pos, edge_labels=labels, ax=self.ax)
        self.ax.set_axis_off(); self.canvas.draw()

    # ---------------- BOUTONS ALGORITHMES ----------------
    def creer_boutons_algos(self):
        algos = [
            ("BFS (Largeur)", self.run_bfs), ("DFS (Profondeur)", self.run_dfs),
            ("Coloration Gloutonne", self.run_greedy), ("Coloration Welsh-Powell", self.run_welsh), 
            ("Prim (Racine requise)", self.run_prim), ("Kruskal (Global)", self.run_kruskal), 
            ("Dijkstra (Saisie unique)", self.run_dijkstra), ("Ford-Fulkerson", self.run_ford)
        ]
        for nom, fct in algos:
            tk.Button(self.zone_algos, text=nom, command=fct, font=('Arial', 8), anchor="w", bg="#34495e", fg="white").pack(fill="x", pady=1)

    # ---------------- ALGORITHMES ----------------
    def run_greedy(self):
        if not self.graph.nodes: return
        colors = nx.coloring.greedy_color(self.graph, strategy='independent_set')
        node_colors = {n: self.palette[c % len(self.palette)] for n, c in colors.items()}
        self.dessiner_graphe(node_colors=node_colors)
        self.journaliser("Coloration Gloutonne", f"Couleurs: {colors}")

    def run_welsh(self):
        if not self.graph.nodes: return
        colors = nx.coloring.greedy_color(self.graph, strategy='largest_first')
        node_colors = {n: self.palette[c % len(self.palette)] for n, c in colors.items()}
        self.dessiner_graphe(node_colors=node_colors)
        self.journaliser("Welsh-Powell", f"Nb couleurs: {max(colors.values())+1}\n{colors}")

    def run_prim(self):
        start = self.choisir_sommet("Sommet de départ pour Prim :")
        if start:
            comp = nx.node_connected_component(self.graph, start)
            mst = nx.minimum_spanning_tree(self.graph.subgraph(comp), algorithm='prim')
            self.dessiner_graphe(highlight_edges=list(mst.edges()))
            self.journaliser("Prim", f"Racine: {start}\nPoids: {mst.size(weight='weight')}")

    def run_kruskal(self):
        if not self.graph.edges: return
        mst = nx.minimum_spanning_tree(self.graph, algorithm='kruskal')
        self.dessiner_graphe(highlight_edges=list(mst.edges()))
        self.journaliser("Kruskal", f"Poids Total: {mst.size(weight='weight')}")

    def run_dijkstra(self):
        saisie = simpledialog.askstring("Dijkstra", "Départ,Arrivée (ex: A,B)")
        if not saisie or "," not in saisie: return
        try:
            s, e = [x.strip() for x in saisie.split(",")]
            path = nx.shortest_path(self.graph, s, e, weight='weight')
            self.dessiner_graphe(highlight_edges=list(zip(path, path[1:])))
            self.journaliser("Dijkstra", f"Chemin {s}->{e}: {path}")
        except: messagebox.showerror("Erreur", "Chemin impossible")

    def run_bfs(self):
        start = self.choisir_sommet("Départ BFS :")
        if start:
            edges = list(nx.bfs_edges(self.graph, start))
            self.dessiner_graphe(highlight_edges=edges)
            self.journaliser("BFS", f"Parcours depuis {start}")

    def run_dfs(self):
        start = self.choisir_sommet("Départ DFS :")
        if start:
            edges = list(nx.dfs_edges(self.graph, start))
            self.dessiner_graphe(highlight_edges=edges)
            self.journaliser("DFS", f"Parcours depuis {start}")

    def run_ford(self):
        saisie = simpledialog.askstring("Ford-Fulkerson", "Source,Puits")
        if not saisie or "," not in saisie: return
        try:
            s, t = [x.strip() for x in saisie.split(",")]
            R = self.graph.to_directed()
            for u,v,d in R.edges(data=True): d['capacity'] = d.get('weight', 1.0)
            val, _ = nx.maximum_flow(R, s, t)
            self.journaliser("Ford-Fulkerson", f"Flot Max {s}->{t} : {val}")
        except: pass

    # ---------------- AUTRES ----------------
    def exporter_tout(self):
        if not self.history_data: return messagebox.showwarning("Vide", "Aucun résultat.")
        path_save = filedialog.asksaveasfilename(defaultextension=".pdf", filetypes=[("PDF", "*.pdf")])
        if not path_save: return
        pdf = FPDF(); pdf.set_auto_page_break(auto=True, margin=15)
        pdf.add_page(); pdf.set_font("Arial", 'B', 24); pdf.cell(0, 60, "Rapport de Traitement", ln=True, align='C')
        pdf.set_font("Arial", '', 12); pdf.cell(0, 10, f"Auteur : {self.user_name}", ln=True, align='C')
        for item in self.history_data:
            pdf.add_page(); pdf.set_fill_color(52, 152, 219); pdf.set_text_color(255, 255, 255)
            pdf.set_font("Arial", 'B', 14); pdf.cell(0, 10, f" Algorithme : {item['titre']}", ln=True, fill=True)
            pdf.ln(5); pdf.set_text_color(0, 0, 0); pdf.set_font("Courier", size=10); pdf.multi_cell(0, 6, item['texte'])
            if os.path.exists(item['image']): pdf.image(item['image'], x=15, w=180)
        pdf.output(path_save); messagebox.showinfo("Succès", "Rapport généré.")

    def supprimer_noeud(self):
        n = self.choisir_sommet("Sommet à supprimer :")
        if n: self.graph.remove_node(n); self.pos.pop(n, None); self.dessiner_graphe()

    def supprimer_arete(self):
        saisie = simpledialog.askstring("Suppression", "U,V")
        if saisie and "," in saisie:
            u, v = [x.strip() for x in saisie.split(",")]
            if self.graph.has_edge(u, v): self.graph.remove_edge(u, v); self.dessiner_graphe()

    def ajouter_noeud_manuel(self):
        n = self.entree_noeud.get().strip()
        if n: self.graph.add_node(n); self.pos[n] = [random.random(), random.random()]; self.dessiner_graphe()

    def ajouter_arete_manuelle(self):
        u, v, w = self.arete_u.get().strip(), self.arete_v.get().strip(), self.arete_w.get().strip()
        if u and v:
            try: self.graph.add_edge(u, v, weight=float(w)); self.pos = nx.spring_layout(self.graph, pos=self.pos, fixed=list(self.pos.keys()) if self.pos else None); self.dessiner_graphe()
            except: pass

    def importer_graphe(self):
        path = filedialog.askopenfilename(filetypes=[("CSV", "*.csv")])
        if path:
            self.graph.clear()
            with open(path, 'r') as f:
                r = csv.DictReader(f)
                for row in r: self.graph.add_edge(row['source'], row['target'], weight=float(row['weight']))
            self.pos = nx.spring_layout(self.graph); self.dessiner_graphe()

    def generer_templates(self):
        d = filedialog.askdirectory()
        if d:
            with open(os.path.join(d, "template.csv"), 'w', newline='') as f:
                w = csv.writer(f); w.writerow(["source", "target", "weight"]); w.writerow(["A", "B", "5"])
            messagebox.showinfo("OK", "Template créé")

    def generer_aleatoire(self):
        self.graph.clear(); G = nx.gnm_random_graph(7, 10)
        for u, v in G.edges(): self.graph.add_edge(str(u), str(v), weight=random.randint(1,12))
        self.pos = nx.spring_layout(self.graph); self.dessiner_graphe()

    def reset_visuel(self): self.dessiner_graphe()
    def clear_results(self): self.zone_traitement.delete('1.0', tk.END); self.history_data = []

    def logout(self):
        if messagebox.askyesno("Déconnexion", "Voulez-vous vraiment vous déconnecter ?"):
            for widget in self.root.winfo_children():
                widget.destroy()
            AuthManager(self.root, start_app)
    
    def exporter_par_date(self):
        # Demander la date à l'utilisateur
        date_saisie = simpledialog.askstring("Exporter par date", "Entrez la date (YYYY-MM-DD) :")
        if not date_saisie:
            return

        # Vérifier la validité de la date
        try:
            datetime.datetime.strptime(date_saisie, '%Y-%m-%d')
        except ValueError:
            messagebox.showerror("Erreur", "Format de date invalide. Utilisez YYYY-MM-DD.")
            return

        # Récupérer les entrées de cette date depuis la DB
        conn = sqlite3.connect("users_labo.db")
        rows = conn.execute(
            "SELECT titre, contenu, image, date FROM history WHERE username=? AND date LIKE ? ORDER BY date",
            (self.user_name, f"{date_saisie}%")
        ).fetchall()
        conn.close()

        if not rows:
            messagebox.showinfo("Aucune donnée", f"Aucun historique trouvé pour le {date_saisie}.")
            return

        # Choisir le chemin de sauvegarde
        path_save = filedialog.asksaveasfilename(
            defaultextension=".pdf", filetypes=[("PDF", "*.pdf")],
            initialfile=f"Archives_{self.user_name}_{date_saisie}.pdf"
        )
        if not path_save: return

        # Générer le PDF
        pdf = FPDF()
        pdf.set_auto_page_break(auto=True, margin=15)
        pdf.add_page()
        pdf.set_font("Arial", 'B', 24)
        pdf.cell(0, 60, f"Archives du {date_saisie}", ln=True, align='C')
        pdf.set_font("Arial", '', 12)
        pdf.cell(0, 10, f"Auteur : {self.user_name}", ln=True, align='C')

        for titre, contenu, image, date in rows:
            pdf.add_page()
            pdf.set_fill_color(52, 152, 219)
            pdf.set_text_color(255, 255, 255)
            pdf.set_font("Arial", 'B', 14)
            # Afficher nom de l'algorithme ET date/heure exacte
            pdf.cell(0, 10, f"Algorithme : {titre}  |  Date & Heure : {date}", ln=True, fill=True)
            pdf.ln(5)
            pdf.set_text_color(0, 0, 0)
            pdf.set_font("Courier", size=10)
            pdf.multi_cell(0, 6, contenu)
            if os.path.exists(image):
                pdf.image(image, x=15, w=180)

        pdf.output(path_save)
        messagebox.showinfo("Succès", f"PDF généré pour le {date_saisie}.")


# --- 4. LANCEMENT ---
if __name__ == "__main__":
    init_db()
    root = tk.Tk()
    def start_app(name):
        for w in root.winfo_children(): w.destroy()
        GraphApp(root, user_name=name)
    
    AuthManager(root, start_app)
    root.mainloop()
