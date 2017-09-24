package gameon.map.graph;

import java.util.function.Supplier;

public enum Direction implements Supplier<String>, Reversible {

    NORTH {
        @Override
        public String getReversal() {
            return SOUTH.get();
        }

        @Override
        public String get() {
            return "North";
        }
    }, SOUTH {
        @Override
        public String getReversal() {
            return NORTH.get();
        }

        @Override
        public String get() {
            return "South";
        }
    }, EAST {
        @Override
        public String getReversal() {
            return WEST.get();
        }

        @Override
        public String get() {
            return "East";
        }
    }, WEST {
        @Override
        public String getReversal() {
            return EAST.get();
        }

        @Override
        public String get() {
            return "West";
        }
    };

}
