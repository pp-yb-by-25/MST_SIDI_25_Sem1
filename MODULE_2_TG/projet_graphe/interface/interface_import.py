# interface_import_complet.py
import tkinter as tk
from tkinter.ttk import Combobox
import networkx as nx
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import matplotlib.pyplot as plt
from tkinter import filedialog, messagebox
import json
import pandas as pd

class InterfaceImport:
    def __init__(self, root, type_graphe):
        self.root = root
        self.type_graphe = type_graphe

        # Fenêtre secondaire
        self.window = tk.Toplevel(self.root)
        self.window.title("Importation et Construction du Graphe")
        self.window.configure(bg="#F5F5F7")
        self.window.state('zoomed')
        self.window.resizable(True, True)

        # Graphe
        self.graphe = nx.DiGraph() if type_graphe=="oriente" else nx.Graph()

        # TITRE
        tk.Label(
            self.window,
            text=f"Type de graphe : {self.type_graphe.capitalize()}",
            font=("Arial", 16, "bold"),
            bg="#F5F5F7", fg="#1C1C1C"
        ).pack(pady=10)

        # FRAME IMPORTATION
        frame_import = tk.LabelFrame(
            self.window, text="Importation et Templates",
            font=("Arial",14,"bold"), bg="#DFF0D8", padx=15, pady=15
        )
        frame_import.pack(side=tk.LEFT, fill=tk.Y, padx=10, pady=10)

        # Combobox template à télécharger
        tk.Label(frame_import, text="Choisir template à télécharger :", bg="#DFF0D8",
                 font=("Arial",12,"bold")).pack(pady=5)
        self.download_template = Combobox(frame_import, values=[
            "Template JSON",
            "Template CSV",
            "Template Excel"
        ], state="readonly", font=("Arial",12))
        self.download_template.pack(pady=5)
        self.download_template.set("Template JSON")

        tk.Button(frame_import, text="Télécharger template",
                  bg="#8E44AD", fg="white", font=("Arial",12,"bold"),
                  activebackground="#71368A", command=self.telecharger_template).pack(pady=10)

        # Importer fichier existant
        tk.Button(frame_import, text="Importer fichier",
                  bg="#4A90E2", fg="white", font=("Arial",12,"bold"),
                  activebackground="#357ABD", command=self.importer_fichier).pack(pady=10)

        # FRAME AJOUT MANUEL
        frame_manual = tk.LabelFrame(
            self.window, text="Ajout manuel",
            font=("Arial",14,"bold"), bg="#FDEBD0", padx=15, pady=15
        )
        frame_manual.pack(side=tk.LEFT, fill=tk.Y, padx=10, pady=10)

        tk.Label(frame_manual, text="Ajouter un sommet :", bg="#FDEBD0", font=("Arial",12)).pack(pady=5)
        self.entry_sommet = tk.Entry(frame_manual, font=("Arial",12))
        self.entry_sommet.pack(pady=2)
        tk.Button(frame_manual, text="Ajouter sommet",
                  bg="#27AE60", fg="white", font=("Arial",12,"bold"),
                  activebackground="#1E8449", command=self.ajouter_sommet).pack(pady=5)

        tk.Label(frame_manual, text="Ajouter un arc (source → destination) :", bg="#FDEBD0", font=("Arial",12)).pack(pady=5)
        self.entry_source = tk.Entry(frame_manual, font=("Arial",12))
        self.entry_source.pack(pady=2)
        self.entry_dest = tk.Entry(frame_manual, font=("Arial",12))
        self.entry_dest.pack(pady=2)
        tk.Label(frame_manual, text="Poids :", bg="#FDEBD0", font=("Arial",12)).pack()
        self.entry_poids = tk.Entry(frame_manual, font=("Arial",12))
        self.entry_poids.pack(pady=2)
        tk.Button(frame_manual, text="Ajouter arc",
                  bg="#F39C12", fg="white", font=("Arial",12,"bold"),
                  activebackground="#D68910", command=self.ajouter_arc).pack(pady=5)

        # FRAME VISUALISATION
        self.frame_visu = tk.LabelFrame(self.window, text="Visualisation du graphe",
                                        font=("Arial",14,"bold"), bg="#FFFFFF")
        self.frame_visu.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True, padx=10, pady=10)

        self.figure, self.ax = plt.subplots(figsize=(12,8))
        self.canvas = FigureCanvasTkAgg(self.figure, master=self.frame_visu)
        self.canvas.get_tk_widget().pack(fill=tk.BOTH, expand=True)

        # BOUTONS RETOUR / QUITTER
        frame_actions = tk.Frame(self.window, bg="#F5F5F7")
        frame_actions.pack(fill=tk.X, pady=10)
        tk.Button(frame_actions, text="Retour",
                  bg="#E74C3C", fg="white", font=("Arial",12,"bold"),
                  activebackground="#C0392B", command=self.retour_interface).pack(side=tk.LEFT, padx=10)
        tk.Button(frame_actions, text="Quitter",
                  bg="#95A5A6", fg="white", font=("Arial",12,"bold"),
                  activebackground="#7F8C8D", command=self.root.quit).pack(side=tk.RIGHT, padx=10)

        # Signature
        tk.Label(self.window, text="YOUSSEF BAHIDA --- MST-SIDI-25",
                 font=("Arial",12,"italic"), bg="#F5F5F7", fg="#555555").pack(side=tk.BOTTOM, pady=5)

        self.afficher_graphe()

    # --- Import fichier automatique ---
    def importer_fichier(self):
        file_path = filedialog.askopenfilename()
        if not file_path: return
        try:
            if file_path.endswith(".json"):
                with open(file_path, "r", encoding="utf-8") as f:
                    data = json.load(f)
                for node in data.get("sommets", []):
                    self.graphe.add_node(node)
                for arc in data.get("arcs", []):
                    src = arc["source"]
                    dest = arc["destination"]
                    weight = arc.get("poids", 1)
                    self.graphe.add_edge(src, dest, weight=weight)

            elif file_path.endswith(".csv"):
                df = pd.read_csv(file_path, dtype={'source': str, 'destination': str, 'poids': float})
                df = df.fillna({'poids': 1})
                for node in pd.unique(df[['source','destination']].values.ravel()):
                    self.graphe.add_node(node)
                for _, row in df.iterrows():
                    src = row['source']
                    dest = row['destination']
                    weight = row['poids'] if pd.notna(row['poids']) else 1
                    self.graphe.add_edge(src, dest, weight=weight)

            elif file_path.endswith((".xlsx", ".xls")):
                df = pd.read_excel(file_path, dtype={'source': str, 'destination': str, 'poids': float})
                df = df.fillna({'poids': 1})
                for node in pd.unique(df[['source','destination']].values.ravel()):
                    self.graphe.add_node(node)
                for _, row in df.iterrows():
                    src = row['source']
                    dest = row['destination']
                    weight = row['poids'] if pd.notna(row['poids']) else 1
                    self.graphe.add_edge(src, dest, weight=weight)
            else:
                tk.messagebox.showerror("Erreur", "Type de fichier non supporté !")
                return
        except Exception as e:
            tk.messagebox.showerror("Erreur", f"Impossible de lire le fichier\n{e}")
        self.afficher_graphe()

    # --- Télécharger template ---
    def telecharger_template(self):
        choix = self.download_template.get()
        file_path = filedialog.asksaveasfilename(defaultextension=".json" if "JSON" in choix else (".csv" if "CSV" in choix else ".xlsx"))
        if not file_path: return
        try:
            if choix == "Template JSON":
                data = {"sommets": ["A","B","C"], "arcs":[{"source":"A","destination":"B","poids":1},{"source":"B","destination":"C","poids":2}]}
                with open(file_path, "w", encoding="utf-8") as f:
                    json.dump(data, f, indent=4)
            elif choix == "Template CSV":
                df = pd.DataFrame({"source":["A","B"], "destination":["B","C"], "poids":[1,2]})
                df.to_csv(file_path, index=False)
            elif choix == "Template Excel":
                df = pd.DataFrame({"source":["A","B"], "destination":["B","C"], "poids":[1,2]})
                df.to_excel(file_path, index=False)
            tk.messagebox.showinfo("Succès", f"{choix} téléchargé !")
        except Exception as e:
            tk.messagebox.showerror("Erreur", f"Impossible de créer le template\n{e}")

    # --- Ajout manuel ---
    def ajouter_sommet(self):
        node = self.entry_sommet.get()
        if node:
            self.graphe.add_node(node)
            self.entry_sommet.delete(0, tk.END)
            self.afficher_graphe()

    def ajouter_arc(self):
        src = self.entry_source.get()
        dest = self.entry_dest.get()
        try:
            weight = float(self.entry_poids.get())
        except:
            weight = 1
        if src and dest:
            self.graphe.add_edge(src, dest, weight=weight)
            self.entry_source.delete(0, tk.END)
            self.entry_dest.delete(0, tk.END)
            self.entry_poids.delete(0, tk.END)
            self.afficher_graphe()

    # --- Visualisation ---
    def afficher_graphe(self):
        self.ax.clear()

        # Layout Kamada-Kawai pour graphe lisible
        pos = nx.kamada_kawai_layout(self.graphe)

        # Nœuds et labels
        nx.draw_networkx_nodes(self.graphe, pos, ax=self.ax, node_color="white", edgecolors="black", node_size=800)
        nx.draw_networkx_labels(self.graphe, pos, ax=self.ax, font_size=14, font_color="black")

        # Arêtes avec flèches et arcs
        nx.draw_networkx_edges(
            self.graphe, pos, ax=self.ax,
            arrows=self.type_graphe=="oriente",
            arrowstyle='-|>',
            arrowsize=20,
            edge_color='black',
            width=2,
            connectionstyle='arc3,rad=0.2'
        )

        # Poids au-dessus des arcs avec décalage
        edge_labels = {(u, v): d['weight'] for u, v, d in self.graphe.edges(data=True)}
        for (u, v), label in edge_labels.items():
            x = (pos[u][0] + pos[v][0]) / 2
            y = (pos[u][1] + pos[v][1]) / 2 + 0.05  # décalage vertical
            self.ax.text(x, y, str(label), fontsize=12, color='black', ha='center', va='center')

        self.ax.set_axis_off()
        self.canvas.draw()

    # --- Retour ---
    def retour_interface(self):
        self.window.destroy()
        self.root.deiconify()
