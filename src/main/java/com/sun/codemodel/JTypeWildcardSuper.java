package com.sun.codemodel;

import java.util.*;

public class JTypeWildcardSuper extends JClass {

    private final JClass bound;

    public JTypeWildcardSuper(JClass bound) {
        super(bound.owner());
        this.bound = bound;
    }

    @Override
    public String name() {
        return "? super " + bound.name();
    }

    @Override
    public String fullName() {
        return "? super " + bound.fullName();
    }

    @Override
    public JPackage _package() {
        return null;
    }

    @Override
    public JClass _extends() {
        if (bound != null)
            return bound;
        else
            return owner().ref(Object.class);
    }

    @Override
    public Iterator<JClass> _implements() {
        return bound._implements();
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    protected JClass substituteParams(JTypeVar[] variables, List<JClass> bindings) {
        JClass nb = bound.substituteParams(variables, bindings);
        if (nb == bound)
            return this;
        else
            return new JTypeWildcardSuper(nb);
    }

    @Override
    public void generate(JFormatter f) {
        if (bound._extends() == null)
            f.p("?"); // instead of "? super Object"
        else
            f.p("? super").g(bound);
    }
}