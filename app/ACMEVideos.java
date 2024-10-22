package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import dados.Acervo;
import dados.Filme;
import dados.Seriado;
import dados.Video;

public class ACMEVideos {

    private Acervo acervo;
    private Video video;

    public ACMEVideos(){
        acervo = new Acervo();
    }


    public void processar() {
        leArquivos();
        getTituloMaisLongo();
    }

    public void leArquivos() {
        String path = "dadosentrada.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();
            while (linha != null) {

               
                String[] vetor = linha.split(";");
                Integer FilmeSerie = Integer.parseInt(vetor[0]);
                Integer codigo = Integer.parseInt(vetor[1]);

                boolean found = false;
                for (Video v : acervo.getVideo()) {
                    if (codigo.equals(v.getCodigo())) {
                        System.out.println("1: Erro - codigo de video repetido.");
                        found = true;
                        break;
                    }
                }
                if (found) {
                    linha = br.readLine();
                    continue;
                }

                String titulo = vetor[2];

                String confere = Integer.toString(codigo);
                if (Character.getNumericValue(confere.charAt(0)) != FilmeSerie) {
                    linha = br.readLine();
                    continue;
                }


                if (FilmeSerie == 1) {                              //se for filme

                    if(vetor.length != 5){
                        break;
                    }

                    String diretor = vetor[3];
                    Double duracao = Double.parseDouble(vetor[4]);

                    video = new Filme(codigo,titulo,diretor,duracao);

                } else {                                            //se for seriado

                    if(vetor.length != 6){
                        break;
                    }

                    Integer anoInicio = Integer.parseInt(vetor[3]);
                    Integer anoFim = Integer.parseInt(vetor[4]);
                    Integer numEpisodios = Integer.parseInt(vetor[5]);

                    video = new Seriado(codigo,titulo,anoInicio,anoFim,numEpisodios);
                }


                System.out.println(video.geraTexto());                  // DEVE SAIR NO ARQUIVO .TXT
                acervo.addVideo(video);
                linha = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void getTituloMaisLongo(){

        if(acervo.getVideo().isEmpty()){
            System.out.println("2: Erro - nenhum vídeo cadastrado.");
        }

        Video maisLongo = acervo.getVideo().getFirst();
        for (Video v : acervo.getVideo()) {
            if(v.getTitulo().length() > maisLongo.getTitulo().length()){
                maisLongo = v;
            }
        }
        String tituloMaisLongo = "2: "+  maisLongo.getCodigo()+ " - " +maisLongo.getTitulo();
        System.out.println(tituloMaisLongo);
    }
}
