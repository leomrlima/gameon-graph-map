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
    }, EAST {
        @Override
        public String getReversal() {
            return WEST.get();
        }

        @Override
        public String get() {
            return "e";
        }
    }, WEST {
        @Override
        public String getReversal() {
            return EAST.get();
        }

        @Override
        public String get() {
            return "w";
        }
    };

}
