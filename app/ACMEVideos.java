package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import dados.Acervo;
import dados.Filme;
import dados.Seriado;
import dados.Video;

public class ACMEVideos {

    private Acervo acervo;
    private Video video;

    public ACMEVideos() {
        acervo = new Acervo();
    }


    public void processar() {
        leArquivos();
        getTituloMaisLongo();
        getCustoMaisBaixo();
        getMaiorSeriado();
        getDiretorMaisFilmes();
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

                    if (vetor.length != 5) {
                        break;
                    }

                    String diretor = vetor[3];
                    Double duracao = Double.parseDouble(vetor[4]);


                    video = new Filme(codigo, titulo, diretor, duracao);

                } else {                                            //se for seriado

                    if (vetor.length != 6) {
                        break;
                    }

                    Integer anoInicio = Integer.parseInt(vetor[3]);
                    Integer anoFim = Integer.parseInt(vetor[4]);
                    Integer numEpisodios = Integer.parseInt(vetor[5]);

                    video = new Seriado(codigo, titulo, anoInicio, anoFim, numEpisodios);
                }


                System.out.println("1: " + video.geraTexto());                  // DEVE SAIR NO ARQUIVO .TXT
                acervo.addVideo(video);
                linha = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("1: Erro: " + e.getMessage());
        }
    }

    public void getTituloMaisLongo() {

        if (acervo.getVideo().isEmpty()) {
            System.out.println("2: Erro - nenhum vídeo cadastrado.");
            return;
        }

        Video maisLongo = acervo.getVideo().getFirst();
        for (Video v : acervo.getVideo()) {
            if (v.getTitulo().length() > maisLongo.getTitulo().length()) {
                maisLongo = v;
            }
        }
        String tituloMaisLongo = "2: " + maisLongo.getCodigo() + " - " + maisLongo.getTitulo();
        System.out.println(tituloMaisLongo);
    }

    public void getCustoMaisBaixo() {
        if (acervo.getVideo().isEmpty()) {
            System.out.println("3: Erro - nenhum vídeo cadastrado.");
            return;
        }

        Video custoMaisBaixo = acervo.getVideo().getFirst();
        for (Video v : acervo.getVideo()) {
            if (custoMaisBaixo.calculaCusto() > v.calculaCusto()) {
                custoMaisBaixo = v;
            }
        }

        String videoCustoMaisBaixo = "3: " + custoMaisBaixo.getCodigo() + " - " + custoMaisBaixo.getTitulo()
                + " " + String.format("%.2f", custoMaisBaixo.calculaCusto());
        System.out.println(videoCustoMaisBaixo);
    }

    public void getMaiorSeriado() {
        if (acervo.getVideo().isEmpty()) {
            System.out.println("4: Erro - nenhum seriado cadastrado.");
            return;
        }

        Seriado maiorSeriado = null;

        for (Video v : acervo.getVideo()) {
            if (v instanceof Seriado) {
                Seriado seriado = (Seriado) v;
                if (maiorSeriado == null || seriado.calculaCusto() > maiorSeriado.calculaTempo()) {
                    maiorSeriado = seriado;
                }
            }
        }

        if (maiorSeriado == null) {
            System.out.println("4: Erro - nenhum seriado encontrado.");
            return;
        }

        String maiorSeriadoTempo = "4: " + maiorSeriado.getCodigo() + " - " + maiorSeriado.getTitulo() + " = " + maiorSeriado.calculaTempo();
        System.out.println(maiorSeriadoTempo);
    }

    public void getDiretorMaisFilmes() {
        if (acervo.getVideo().isEmpty()) {
            System.out.println("5: Erro - nenhum filme cadastrado.");
            return;
        }

        int numeroFilmes = 0;
        Filme diretorMaisFilme = null;
        int countMovies = 0;

        for (Video v : acervo.getVideo()) {
            if (v instanceof Filme) {
                countMovies = 0;

                for(Video vd : acervo.getVideo()) {
                    if(vd instanceof Filme) {
                        if(((Filme) vd).getDiretor().equals(((Filme) v).getDiretor())) {
                            countMovies++;
                        }
                    }

                    if(numeroFilmes < countMovies) {
                        numeroFilmes = countMovies;
                        diretorMaisFilme = (Filme) v;
                    }
                }
            }
        }
        if(diretorMaisFilme == null) {
            System.out.println("5: Erro - nenhum filme cadastrado.");
            return;
        }
        System.out.println("5: " + diretorMaisFilme.getDiretor() + " - " + numeroFilmes);
    }
}
