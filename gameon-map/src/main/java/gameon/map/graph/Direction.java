package gameon.map.graph;

import java.util.function.Supplier;

public enum Direction implements Supplier<String>, Reversible {

    NORH {
        @Override
        public String getReversal() {
            return SOUTH.get();
        }

        @Override
        public String get() {
            return "n";
        }
    }, SOUTH {
        @Override
        public String getReversal() {
            return NORH.get();
        }

        @Override
        public String get() {
            return "s";
        }
    };

}
