

public class Cidade
{
    private boolean foiVisitada = false;
    public double coordX;
    public double coordY;
    public Cidade(double coordX, double coordY)
    {
        this.coordX = coordX;
        this.coordY = coordY;
    }
    public double[][] getCoordenadas(){
        return new double[][]{ new double[]{ this.coordX }, new double[]{ this.coordY }};
    }

    public String toString(){
        return String.format("(%,.2f; %,.2f)", this.coordX, this.coordY);
    }

    public static double distancia(Cidade[] rota) {
        int qtdCidades = rota.length;
        // distancia entre a ultima cidade e a primeira
        double distancia = rota[qtdCidades-1].distancia(rota[0]);
        //calcular a distancia entre as demais cidades
        for(int i = 1; i < qtdCidades; i++)
            distancia += rota[i-1].distancia(rota[i]);
        return distancia;
    }

    // distancia euclidiana
    public double distancia(final Cidade para) {
        return Math.sqrt(_distancia(para));
    }

    // nao ha necessidade de raiz para comparar distancias
    public double _distancia(final Cidade destino) {
        double dx = this.coordX-destino.coordX;
        double dy = this.coordY-destino.coordY;
        return (dx*dx)+(dy*dy);
    }

    public boolean foiVisitada() {
        return foiVisitada;
    }

    public void visitar() {
        foiVisitada = true;
    }
}