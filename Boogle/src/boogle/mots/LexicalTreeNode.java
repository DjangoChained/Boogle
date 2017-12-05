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

    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }
    
    /**
     * Obtenir un noeud enfant.
     * @param c Lettre associée au noeud enfant.
     * @return Noeud enfant.
     */
    protected LexicalTreeNode getChild(Character c) {
        return Stream.of(children).filter(l -> l != null && c.equals(l.getLetter())).findAny().get();
    }

    /**
     * Tester si ce noeud contient un noeud enfant avec la lettre correspondante.
     * @param c Lettre associée au noeud enfant à rechercher.
     * @return True s'il y a un noeud enfant avec la lettre correspondante.
     */
    protected boolean containsChild(Character c) {
        return Stream.of(children).anyMatch(l -> l != null && c.equals(l.getLetter()));
    }

    /**
     * Obtenir un noeud enfant, en le créant s'il n'existe pas encore.
     * @param c Lettre associée au noeud enfant.
     * @return Noeud enfant.
     */
    protected LexicalTreeNode getOrCreateChild(Character c) {
        if(containsChild(c)) return getChild(c);
        else {
            LexicalTreeNode l = new LexicalTreeNode(c);
            for(int i = 0; i < children.length; i++) if(children[i] == null) {
                children[i] = l; break;
            }
            return l;
        }
    }

    public boolean hasChildren() {
        return Stream.of(children).anyMatch(l -> l != null);
    }
    
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
        if(!containsChild(word.charAt(0))) return false;
        else if(word.length() == 1 && letter.equals(word.charAt(0))) return true;
        else return getChild(word.charAt(0)).contains(word.substring(1));
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
