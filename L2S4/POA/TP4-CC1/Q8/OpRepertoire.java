enum Operation {
    AJOUT, SUPPRESSION
}

public class OpRepertoire {
    private Operation operation;
    private Noeud noeud;

    public OpRepertoire(Operation operation, Noeud element) {
        this.operation = operation;
        this.noeud = element;
    }

    public Operation donneOperation() {
        return operation;
    }

    public Noeud donneNoeud() {
        return noeud;
    }

    @Override
    public String toString() {
        return "OpRepertoire{" +
            "operation=" + operation +
            ", noeud=" + noeud +
            '}';
    }
}
