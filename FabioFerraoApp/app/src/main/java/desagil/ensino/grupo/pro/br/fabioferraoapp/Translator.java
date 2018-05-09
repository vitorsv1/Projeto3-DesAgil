package desagil.ensino.grupo.pro.br.fabioferraoapp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Translator {
    // ESTA CLASSE NÃO PODE SER MODIFICADA!
    private class Node {
        private char value;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node() {
            this.value = ' ';
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }
        public Node(char value) {
            this.value = value;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        public char getValue() {
            return value;
        }
        public Node getParent() {
            return parent;
        }
        public void setParent(Node parent) {
            this.parent = parent;
        }
        public Node getLeftChild() {
            return leftChild;
        }
        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }
        public Node getRightChild() {
            return rightChild;
        }
        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }


    // ESTE CONJUNTO DE ATRIBUTOS NÃO PODE SER MODIFICADO, OU
    // SEJA, NÃO É PERMITIDO ADICIONAR NEM REMOVER ATRIBUTOS!
    private Node root;
    private HashMap<Character, Node> map;


    // ESTE CONSTRUTOR DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public Translator() {
        this.root = new Node();
        this.map = new HashMap<Character, Node>();

        String abc = " etianmsurwdkgohvf l pjbxcyzq  54 3   2       16       7   8 90";
        Node[] nodes = new Node[abc.length()];

        for (int i = 0; i < abc.length(); i++) {
            char value = abc.charAt(i);
            Node node = new Node(value);
            nodes[i] = node;

            if (value != ' ') {
                this.map.put(value, node);
            }
        }

        for (int i = 0; i < abc.length(); i++) {
            if (i > 0) {
                nodes[i].setParent(nodes[((i - 1)/2)]);
            }
            if (i < 31) {
                nodes[i].setLeftChild(nodes[(2*i) + 1]);
                nodes[i].setRightChild(nodes[(2*i) + 2]);
            }
        }
        this.root = nodes[0];
    }


    // ESTE MÉTODO DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public char morseToChar(String code) {
        Node current = root;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '.') {
                current = current.getLeftChild();
            }
            else if (code.charAt(i) == '-') {
                current = current.getRightChild();
            }
            else {return ' ';}
        }
        return current.getValue();
    }


    // ESTE MÉTODO DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public String charToMorse(char c) {
        Node current = new Node();
        Node previous = new Node();
        String morse = "";
        current = map.get(c);
        previous = map.get(c);

        while (current.getParent() != null) {
            current = current.getParent();
            if (current.getLeftChild() == previous) {
                morse += ".";
                previous = previous.getParent();
            }
            else if (current.getRightChild() == previous) {
                morse += "-";
                previous = previous.getParent();
            }
        }
        String morse_result = "";

        for (int i = morse.length() - 1; i >= 0; i--) {
            morse_result = morse_result + morse.charAt(i);
        }
        return morse_result;
    }


    // ESTE MÉTODO DEVE SER PREENCHIDO DE ACORDO COM O ENUNCIADO!
    public LinkedList<String> getCodes() {
        return null;
    }
}

