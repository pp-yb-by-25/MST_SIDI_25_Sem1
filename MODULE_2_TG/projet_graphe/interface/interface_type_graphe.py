# interface_type_graphe.py
import tkinter as tk
from interface.interface_import import InterfaceImport

class InterfaceTypeGraphe:
    def __init__(self):
        self.root = tk.Tk()
        self.root.title("Sélection du type de graphe")
        self.root.geometry("400x250")
        self.root.configure(bg="#F5F5F7")

        tk.Label(
            self.root, text="Choisir le type de graphe",
            font=("Arial",14,"bold"), bg="#F5F5F7"
        ).pack(pady=20)

        tk.Button(
            self.root, text="Graphe Orienté",
            bg="#4A90E2", fg="white",
            font=("Arial",12,"bold"),
            command=lambda: self.ouvrir_import("oriente")
        ).pack(pady=5)

        tk.Button(
            self.root, text="Graphe Non Orienté",
            bg="#4A90E2", fg="white",
            font=("Arial",12,"bold"),
            command=lambda: self.ouvrir_import("non_oriente")
        ).pack(pady=5)

        tk.Label(
            self.root,
            text="YOUSSEF BAHIDA --- MST-SIDI-25",
            font=("Arial",10,"italic"),
            bg="#F5F5F7", fg="#555555"
        ).pack(side=tk.BOTTOM, pady=5)

        self.root.mainloop()

    def ouvrir_import(self, type_graphe):
        self.root.withdraw()  # cache l'interface type_graphe
        InterfaceImport(self.root, type_graphe)
