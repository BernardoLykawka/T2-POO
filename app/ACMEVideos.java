package app;

import dados.Acervo;

public class ACMEVideos {

    private Acervo acervo;
    private Video video;

    public void processar() {
        leArquivos();
    }

    public void leArquivos() {
        String path = "dadosentrada.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();
            while (linha != null) {

                String[] vetor = linha.split(";");
                Integer codigo = Integer.parseInt(vetor[0]);

                if (codigo == acervo.getVideo().getCodigo()) {
                    break;
                }
                String titulo = vetor[1];

                if ((codigo / (codigo.size() * 10)) == 1) {       //se for filme

                    String diretor = vetor[2];
                    Double duracao = Double.parseDouble(vetor[3]);
                    //criar filme, primeiro fazer construtor

                } else {                                           //se for seriado

                    Integer anoInicio = Integer.parseInt(vetor[2]);
                    Integer anoFim = Integer.parseInt(vetor[3]);
                    Integer numEpisodios = Integer.parseInt(vetor[4]);
                    //criar Serie , primeiro fazer construtor

                }


                acervo.addVideo();
                linha = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
