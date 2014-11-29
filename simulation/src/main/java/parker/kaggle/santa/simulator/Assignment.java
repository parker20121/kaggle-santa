package parker.kaggle.santa.simulator;

/**
 *
 * @author Matt Parker
 */
public class Assignment {

    Elf elf;
    Toy toy;
    long startTime;
    
    public Assignment( Elf elf, Toy toy, long startTime ){
        this.elf = elf;
        this.toy = toy;
        this.startTime = startTime;
    }

    public Elf getElf() {
        return elf;
    }

    public void setElf(Elf elf) {
        this.elf = elf;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    
}
