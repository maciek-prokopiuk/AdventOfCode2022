package pl.mcprok.day2;

public enum Figure {

    ROCK{
        @Override
        boolean beat(Figure figure) {
            return figure == SCISSORS;
        }

        @Override
        Figure winTo() {
            return SCISSORS;
        }

        @Override
        Figure loseTo() {
            return PAPER;
        }

        @Override
        Figure drawTo() {
            return ROCK;
        }

        @Override
        int points() {
            return 1;
        }
    },
    PAPER {
        @Override
        boolean beat(Figure figure) {
            return figure == ROCK;
        }

        @Override
        Figure winTo() {
            return  ROCK;
        }

        @Override
        Figure loseTo() {
            return SCISSORS;
        }

        @Override
        Figure drawTo() {
            return PAPER;
        }

        @Override
        int points() {
            return 2;
        }
    },
    SCISSORS {
        @Override
        boolean beat(Figure figure) {
            return figure == PAPER;
        }

        @Override
        Figure winTo() {
            return PAPER;
        }

        @Override
        Figure loseTo() {
            return ROCK;
        }

        @Override
        Figure drawTo() {
            return SCISSORS;
        }

        @Override
        int points() {
            return 3;
        }
    };

    abstract boolean beat(Figure figure);
    abstract Figure winTo();
    abstract Figure loseTo();
    abstract Figure drawTo();
    abstract int points();
}
