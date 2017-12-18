/*
 * Copyright (C) 2017 rouchete and waxinp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package boogle.mots;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * La classe LexicalTreeNode permet de stocker de façon compacte et d'accéder
 * rapidement à un ensemble de mots.
 * @author rouchete
 */
public class LexicalTreeNode {

    public static final int ALPHABET_SIZE = 26;
    private boolean isWord; // vrai si le noeud courant est la fin d'un mot valide
    private LexicalTreeNode[] children; // les sous-arbres
    private Character letter;

    /**
     * Crée un arbre vide (sans aucun mot)
     */
    public LexicalTreeNode() {
        this.children = new LexicalTreeNode[ALPHABET_SIZE];
        this.isWord = false;
    }

    public LexicalTreeNode(char letter) {
        this();
        this.letter = letter;
    }

    public LexicalTreeNode(char letter, boolean isWord) {
        this(letter);
        this.isWord = isWord;
    }
    
    public char getLetter() {
        return letter;
    }
    
    /**
     * Indique si le noeud courant est situé à l'extrémité d'un mot valide
     *
     * @return True si le noeud est l'extrémité d'un mot.
     */
    public boolean isWord() {
        return letter != null && isWord;
    }

    /** 
     * Définir l'état de mot valide d'un noeud.
     * @param isWord État de mot valide à définir. True si le noeud représente la fin d'un mot valide.
     */
    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }
    
    /**
     * Obtenir un noeud enfant.
     * @param c Lettre associée au noeud enfant.
     * @return Noeud enfant.
     */
    public LexicalTreeNode getChild(Character c) {
        return Stream.of(children).filter(l -> l != null && c.equals(l.getLetter())).findAny().get();
    }

    /**
     * Tester si ce noeud contient un noeud enfant avec la lettre correspondante.
     * @param c Lettre associée au noeud enfant à rechercher.
     * @return True s'il y a un noeud enfant avec la lettre correspondante.
     */
    public boolean containsChild(Character c) {
        return Stream.of(children).anyMatch(l -> l != null && c.equals(l.getLetter()));
    }

    /**
     * Obtenir un noeud enfant, en le créant s'il n'existe pas encore.
     * @param c Lettre associée au noeud enfant.
     * @return Noeud enfant.
     */
    public LexicalTreeNode getOrCreateChild(Character c) {
        if(containsChild(c)) return getChild(c);
        else {
            LexicalTreeNode l = new LexicalTreeNode(c);
            for(int i = 0; i < children.length; i++) if(children[i] == null) {
                children[i] = l; break;
            }
            return l;
        }
    }

    /**
     * Tester si le noeud a des noeuds enfants.
     * @return True si le noeud a des noeuds enfants, False si c'est une impasse.
     */
    public boolean hasChildren() {
        return Stream.of(children).anyMatch(l -> l != null);
    }

    /**
     * Récupérer tous les noeuds enfants.
     * @return Liste de noeuds enfants.
     */
    public List<LexicalTreeNode> getAllChildren() {
        return Stream.of(children).filter(l -> l != null).collect(Collectors.toList());
    }

    /**
     * Place le mot spécifié dans l'arbre.
     *
     * @param word Mot à ajouter.
     */
    public void add(String word) {
        if(word.length() == 1) this.getOrCreateChild(word.charAt(0)).setIsWord(true);
        else this.getOrCreateChild(word.charAt(0)).add(word.substring(1));
    }

    /**
     * Teste si l'arbre lexical contient le mot spécifié.
     *
     * @param word Mot à rechercher.
     * @return <code>true</code> si <code>o</code> est un mot (String) contenu
     * dans l'arbre, <code>false</code> si <code>o</code> n'est pas une instance
     * de String ou si le mot n'est pas dans l'arbre lexical.
     */
    public boolean contains(String word) {
        if(word.length() < 1) return isWord;
        if(!containsChild(word.charAt(0))) return false;
        else if(word.length() == 1 && isWord && letter.equals(word.charAt(0))) return true;
        else return getChild(word.charAt(0)).contains(word.substring(1));
    }

    /**
     * Représentation textuelle du noeud d'arbre lexical.
     * @return Représentation textuelle des mots concevables depuis ce noeud.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(this.isWord()) sb.append(this.letter).append("\n");
        if(hasChildren())
            if(letter != null) getAllChildren().stream().forEach(l -> sb.append(this.letter).append(l.toString()));
            else getAllChildren().stream().forEach(l -> sb.append(l.toString()));
        return sb.toString();
    }
    
    /**
     * Crée un arbre lexical qui contains tous les mots du fichier spécifié.
     *
     * @param fichier Fichier à analyser.
     * @return Arbre lexical contenant tous les mots du fichier spécifié.
     * @throws java.io.IOException
     */
    public static LexicalTreeNode load(String fichier) throws IOException {
        LexicalTreeNode l = new LexicalTreeNode();
        for(String word : Files.readAllLines(Paths.get(fichier))) l.add(word);
        return l;
    }
}
