import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class TwoOptSolver extends Teste {
    private Cidade[] rota;
    private double melhorDistancia;
    private int nCidades;

    private ArrayList<Cidade[]> melhoresRotas = new ArrayList<>();

    public TwoOptSolver(Cidade[] rota) {
        this.rota = rota;
        nCidades = rota.length;
    }

    private Cidade[] ThreeOptSolve() {
        int nMelhorias = 0;
        melhorDistancia = Cidade.distancia(rota);
        while (nMelhorias < 20) {
            Cidade[] otimoLocal = rota.clone();
            double distanciaOtimoLocal = Double.POSITIVE_INFINITY;
            for (int i = 0; i <= nCidades*2; i++) {
                Cidade[] movimentacao = SubArray(otimoLocal, i, nCidades);
                ArrayList<Cidade[]> rotas = new ArrayList<>();
                rotas.add(movimentacao.clone());
                rotas.add(movimentacao.clone()); Swap(rotas.get(1), 3, 4);
                rotas.add(movimentacao.clone()); Swap(rotas.get(2), 1, 2);
                rotas.add(movimentacao.clone()); Swap(rotas.get(3), 1, 2); Swap(rotas.get(3), 3, 4);
                rotas.add(movimentacao.clone()); Swap(rotas.get(4), 1, 3); Swap(rotas.get(4), 2, 4);
                rotas.add(movimentacao.clone()); Swap(rotas.get(5), 1, 3); Swap(rotas.get(5), 2, 4); Swap(rotas.get(5), 3, 4);
                rotas.add(movimentacao.clone()); Swap(rotas.get(6), 1, 4); Swap(rotas.get(6), 2, 3); Swap(rotas.get(6), 3, 4);
                rotas.add(movimentacao.clone()); Swap(rotas.get(7), 1, 4); Swap(rotas.get(7), 2, 3);
                for(int j =0; j<rotas.size(); j++){
                    if(distanciaOtimoLocal > Cidade.distancia(rotas.get(j))){
                        otimoLocal = rotas.get(j);
                        distanciaOtimoLocal = Cidade.distancia(rotas.get(j));
                    }
                }
            }
            if (distanciaOtimoLocal <= melhorDistancia) {
                rota = otimoLocal;
                melhorDistancia = distanciaOtimoLocal;
                melhoresRotas.add(otimoLocal);
            }
            nMelhorias++;
        }
        ArrayList<Cidade[]> melhoresVizinhos = new ArrayList<>();
        melhoresVizinhos.add(rota);
        for (int i = 0; i < melhoresRotas.size(); i++) {
            if (Cidade.distancia(melhoresRotas.get(i)) <= melhorDistancia
                    && !melhoresRotas.get(i).equals(rota)) {
                melhoresVizinhos.add(melhoresRotas.get(i));
            }
        }
        return melhoresVizinhos.get(new Random().nextInt(melhoresVizinhos.size()));
    }

    public Cidade[] TwoOptSolve() {
        int nMelhorias = 0;
        while (nMelhorias < 20) {
            melhorDistancia = Cidade.distancia(rota);
            for (int i = 0; i < nCidades - 1; i++) {
                for (int k = i + 1; k < nCidades; k++) {
                    Cidade[] novaRota = TwoOptSwap(i, k);
                    verificaNovaDistancia(novaRota);
                }
            }
            nMelhorias++;
        }
        ArrayList<Cidade[]> melhoresVizinhos = new ArrayList<>();
        melhoresVizinhos.add(rota);
        for (int i = 0; i < melhoresRotas.size(); i++) {
            if (Cidade.distancia(melhoresRotas.get(i)) <= melhorDistancia
                    && !melhoresRotas.get(i).equals(rota)) {
                melhoresVizinhos.add(melhoresRotas.get(i));
            }
        }
        return melhoresVizinhos.get(new Random().nextInt(melhoresVizinhos.size()));

    }

    private Cidade[] TwoOptSwap(int i, int k) {
        Cidade[] retorno = new Cidade[rota.length];
        for (int c = 0; c <= i - 1; c++) {
            retorno[c] = rota[c];
        }
        for (int c = i, dec = 0; c <= k; c++, dec++) {
            retorno[c] = rota[k - dec];
        }
        for (int c = k + 1; c < rota.length; c++) {
            retorno[c] = rota[c];
        }
        return retorno;
    }

    private void verificaNovaDistancia(Cidade[] novaRota) {
        double novaDistancia = Cidade.distancia(novaRota);
        if (novaDistancia <= melhorDistancia) {
            rota = novaRota;
            melhorDistancia = novaDistancia;
            melhoresRotas.add(novaRota);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insira o nome do arquivo de dados (ex.: dados.txt):");
        String nomeArquivo = br.readLine();
        while (!GeradorRotas.existeArquivo(nomeArquivo)) {
            System.out.println("Arquivo não encontrado!\nInsira um novo nome para o arquivo: ");
            nomeArquivo = br.readLine();
        }
        File arquivo = GeradorRotas.getArquivo(nomeArquivo);
        br = new BufferedReader(new FileReader(arquivo.getCanonicalFile()));
        String linhaAtual;
        ArrayList<Cidade> rota = new ArrayList<>();
        while ((linhaAtual = br.readLine()) != null) {
            String[] colunas = linhaAtual.split("\\t");
            rota.add(new Cidade(Double.parseDouble(colunas[0].replace(",", ".")), Double.parseDouble(colunas[1].replace(",", "."))));
        }

        Cidade[] rotaInicial = rota.toArray(new Cidade[rota.size()]);
        System.out.println(Cidade.distancia(rotaInicial));
        printRota(rotaInicial);

        long startTwoOpt = System.nanoTime();
        Cidade[] melhorRota = new TwoOptSolver(rotaInicial).TwoOptSolve();
        long endTwoOpt = System.nanoTime();
        System.out.println(Cidade.distancia(melhorRota));
        printRota(melhorRota);

        long startThreeOpt = System.nanoTime();
        melhorRota = new TwoOptSolver(rotaInicial).ThreeOptSolve();
        long endThreeOpt = System.nanoTime();
        System.out.println(Cidade.distancia(melhorRota));
        printRota(melhorRota);

        System.out.println("TwoOpt executado em: " + String.format("%.5f", (double)((endTwoOpt - startTwoOpt)/(Math.pow(10,9)))) + " segundos para instância de '" + rotaInicial.length + "' cidades");
        System.out.println("ThreeOopt executado em: " +String.format("%.5f", (double)((endThreeOpt-startThreeOpt)/(Math.pow(10,9)))) + " segundos para instância de '" + rotaInicial.length + "' cidades");
    }

    private static void printRota(Cidade[] rota) {
        Plot2DPanel plot = new Plot2DPanel();
        for (int i = 0; i < rota.length; i++) {
            //System.out.println(rota[wrap(i - 1, rota.length)].toString() + " | " + rota[i].toString());
            plot.addLinePlot("a", Color.BLACK,
                    new double[]{rota[wrap(i - 1, rota.length)].coordX, rota[wrap(i - 1, rota.length)].coordY},
                    new double[]{rota[i].coordX, rota[i].coordY}
            );
        }
        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
