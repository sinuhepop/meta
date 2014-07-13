package tk.spop.meta.x;

public interface Bool {

    Bool equals(Bool b);

    Bool and(Bool b);

    Bool or(Bool b);

    Bool not();

    public static final Bool TRUE = new Bool() {

        @Override
        public Bool equals(Bool b) {
            return b;
        }

        @Override
        public Bool and(Bool b) {
            return b;
        }

        @Override
        public Bool or(Bool b) {
            return this;
        }

        @Override
        public Bool not() {
            return FALSE;
        }

    };

    public static final Bool FALSE = new Bool() {

        @Override
        public Bool equals(Bool b) {
            return b.not();
        }

        @Override
        public Bool and(Bool b) {
            return this;
        }

        @Override
        public Bool or(Bool b) {
            return b;
        }

        @Override
        public Bool not() {
            return TRUE;
        }

    };
}
