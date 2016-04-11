import java.io.*;

public class GeradorRotas {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insira o nome do arquivo (ex.: dados.txt):");
        String nomeArquivo = br.readLine();
        if (existeArquivo(nomeArquivo)) {
            System.out.println("O arquivo '" + getArquivo(nomeArquivo).getCanonicalFile() + "' já existe. Deseja sobrescrevê-lo? [S|n] ");
            String confirma = br.readLine();
            if (confirma.toUpperCase().equals("N")) {
                while (existeArquivo(nomeArquivo)) {
                    System.out.println("Insira um novo nome para o arquivo (somente o nome sem o caminho): ");
                    nomeArquivo = br.readLine();
                }
            } else {
                getArquivo(nomeArquivo).delete();
            }
        }
        File arquivo = getArquivo(nomeArquivo);
        arquivo.createNewFile();
        FileWriter fw = new FileWriter(arquivo.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("Insira o número de cidades: ");
        String sNCidades = br.readLine();
        int nCidades = Integer.parseInt(sNCidades);
        for (int i = 0; i < nCidades; i++) {
            bw.write(String.format("%.5f\t%.5f\n", Math.random() * 100, Math.random() * 100));
        }
        bw.close();
    }

    public static boolean existeArquivo(String nomeArquivo) {
        return getArquivo(nomeArquivo).exists();
    }

    public static File getArquivo(String nomeArquivo) {
        return new File( GeradorRotas.class.getProtectionDomain().getCodeSource().getLocation().getPath()  +"../../../data/" + nomeArquivo);
    }

}
