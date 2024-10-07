package fr.uparis.informatique.cpoo5.dactylo;

public class Parametres implements Parametrable {
    
    private final int motsMax, niveauMax, tempsMax, limiteMots;
    private final String mode;
    private final float coeffTimer;
    private final boolean noTimer;

    public Parametres(ParametresBuilder pb){
        this.mode = pb.mode;
        this.noTimer = pb.noTimer;
        this.motsMax = pb.motsMax;
        this.tempsMax = pb.tempsMax;
        this.coeffTimer = pb.coeffTimer;
        this.niveauMax = pb.niveauMax;
        this.limiteMots = pb.limiteMots;
        
    }

    public int getMotsMax() { return this.motsMax; }
    public int getNiveauMax() { return this.niveauMax; }
    public int getTempsMax() { return this.tempsMax; }
    public String getMode() { return this.mode; }
    public float coeffTimer() { return this.coeffTimer; }
    public int getLimiteMots() { return this.limiteMots; }
    public boolean getNoTimer() { return this.noTimer; }


    //Patron builder
    public static class ParametresBuilder {
       	private int motsMax, niveauMax, tempsMax, limiteMots;
        private String mode;
        private float coeffTimer;
        private boolean noTimer;

        public ParametresBuilder(String mode, boolean noTimer){
            this.mode = mode;
            this.noTimer = noTimer;
        }
        
        public ParametresBuilder(){
            this.mode = "normal";
            this.noTimer = noTimer;
        }

        public ParametresBuilder setNiveauMax(int niveauMax){
            this.niveauMax = niveauMax;
            return this;
        }
        public ParametresBuilder setCoeffTimer(float coeffTimer){
            this.coeffTimer = coeffTimer;
            return this;
        }

        public ParametresBuilder setTempsMax(int tempsMax){
            if(noTimer){
                this.tempsMax = 0;
                return this;
            } else {
                this.tempsMax = tempsMax;
                return this;
            }
            
        }

        public ParametresBuilder setMotsMax(int motsMax){
            if(noTimer){
                this.motsMax = motsMax;
                return this;
            } else {
                this.motsMax = 0;
                return this;
            }
            
        }
        
        public ParametresBuilder setLimiteMots(int lim){
            this.limiteMots = lim;
            return this;
        }

        public ParametresBuilder setNoTimer(boolean noTimer){
            this.noTimer = noTimer;
            return this;
        }
    
        public Parametres build(){
            return new Parametres(this);
        }
    }
}
