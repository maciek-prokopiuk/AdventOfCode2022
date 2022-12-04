package pl.mcprok.day2;

public enum Result {
    WIN{
        @Override
        int points() {
            return 6;
        }
    },
    DRAW {
        @Override
        int points() {
            return 3;
        }
    },
    LOSE{
        @Override
        int points() {
            return 0;
        }
    };

    abstract int points();
}
