package com.sun.codemodel;

import java.util.*;

public final class JTypeMultiple extends JClass {

    private final List<JClass> bounds = new ArrayList<>();

    public JTypeMultiple(JCodeModel owner) {
        super(owner);
    }

    public String name() {
        return null;
    }

    public String fullName() {
        return null;
    }

    public JPackage _package() {
        return null;
    }

    /**
     * Adds a bound to this variable.
     * 
     * @return this
     */
    public JTypeMultiple bound(JClass c) {
        bounds.add(c);
        return this;
    }

    public JClass _extends() {
        return null;
    }

    public Iterator<JClass> _implements() {
        return null;
    }

    public boolean isInterface() {
        return false;
    }

    public boolean isAbstract() {
        return false;
    }

    protected JClass substituteParams(JTypeVar[] variables, List<JClass> bindings) {
        throw new UnsupportedOperationException();
    }

    public void generate(JFormatter f) {
        f.g(bounds.get(0));
        for (int i = 1; i < bounds.size(); i++) {
            f.p(" & ").g(bounds.get(i));
        }
    }

}
