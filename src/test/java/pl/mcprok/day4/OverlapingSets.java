package pl.mcprok.day4;

public class OverlapingSets {
    // i-j, k-l
    public boolean fullyOverlaps(int i, int j, int k, int l) {
        if(i <= k && j >= l) {
            return true;
        }
        if(k <= i && l >= j) {
            return true;
        }
        return false;
    }

    public boolean overlaps(int i, int j, int k, int l) {
        return Math.max(i,k) <= Math.min(j,l);
    }

}
