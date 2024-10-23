package app;

import java.io.*;

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
        limpaRelatorio();
        leArquivos();
        getTituloMaisLongo();
        getCustoMaisBaixo();
        getMaiorSeriado();
        getDiretorMaisFilmes();
        getMenorDesvioPadrao();
    }

    public void leArquivos() {
        String path = "dadosentrada.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String linha = br.readLine();
            while (linha != null) {

                try {
                    String[] vetor = linha.split(";");
                    Integer FilmeSerie = Integer.parseInt(vetor[0]);
                    Integer codigo = Integer.parseInt(vetor[1]);

                    boolean found = false;
                    for (Video v : acervo.getVideo()) {
                        if (codigo.equals(v.getCodigo())) {
                            String textoErroRepetido = ("1: Erro - codigo de video repetido.");
                            imprimeTexto(textoErroRepetido);
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


                    String saida1 = ("1: " + video.geraTexto());
                    imprimeTexto(saida1);
                    acervo.addVideo(video);
                    linha = br.readLine();
                } catch (NumberFormatException e) {
                    String textoErro1 = ("1: Erro - Formato de texto.");
                    imprimeTexto(textoErro1);
                    linha = br.readLine();
                }
            }

        } catch (IOException e) {
            String textoErro1 = ("1: Erro: " + e.getMessage());
            imprimeTexto(textoErro1);
        }
    }

    public void getTituloMaisLongo() {

        if (acervo.getVideo().isEmpty()) {
            String textoErro2 = ("2: Erro - nenhum vídeo cadastrado.");
            return;
        }

        Video maisLongo = acervo.getVideo().getFirst();
        for (Video v : acervo.getVideo()) {
            if (v.getTitulo().length() > maisLongo.getTitulo().length()) {
                maisLongo = v;
            }
        }
        String tituloMaisLongo = "2: " + maisLongo.getCodigo() + " - " + maisLongo.getTitulo();
        imprimeTexto(tituloMaisLongo);
    }

    public void getCustoMaisBaixo() {
        if (acervo.getVideo().isEmpty()) {
            String textoErro3 = "3: Erro - nenhum vídeo cadastrado.";
            imprimeTexto(textoErro3);
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
        imprimeTexto(videoCustoMaisBaixo);
    }

    public void getMaiorSeriado() {
        if (acervo.getVideo().isEmpty()) {
            String textoErro4 = ("4: Erro - nenhum seriado cadastrado.");
            imprimeTexto(textoErro4);
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
            String textoErro4 = ("4: Erro - nenhum seriado encontrado.");
            imprimeTexto(textoErro4);
            return;
        }

        String maiorSeriadoTempo = "4: " + maiorSeriado.getCodigo() + " - " + maiorSeriado.getTitulo() + " = " + maiorSeriado.calculaTempo();
        imprimeTexto(maiorSeriadoTempo);
    }

    public void getDiretorMaisFilmes() {
        if (acervo.getVideo().isEmpty()) {
            String textoErro5 = ("5: Erro - nenhum filme cadastrado.");
            imprimeTexto(textoErro5);
            return;
        }

        int numeroFilmes = 0;
        Filme diretorMaisFilme = null;
        int countMovies = 0;

        for (Video v : acervo.getVideo()) {
            if (v instanceof Filme) {
                countMovies = 0;

                for (Video vd : acervo.getVideo()) {
                    if (vd instanceof Filme) {
                        if (((Filme) vd).getDiretor().equals(((Filme) v).getDiretor())) {
                            countMovies++;
                        }
                    }

                    if (numeroFilmes < countMovies) {
                        numeroFilmes = countMovies;
                        diretorMaisFilme = (Filme) v;
                    }
                }
            }
        }
        if (diretorMaisFilme == null) {
            String textoErro5 = ("5: Erro - nenhum filme cadastrado.");
            imprimeTexto(textoErro5);
            return;
        }
        String saida5 = "5: " + diretorMaisFilme.getDiretor() + " - " + numeroFilmes;
        imprimeTexto(saida5);
    }

    public void getMenorDesvioPadrao() {
        double somaCusto = 0;
        int contador = 0;

        for (Video v : acervo.getVideo()) {
            somaCusto += v.calculaCusto();
            contador++;
        }

        double mediaCusto = somaCusto / contador;

        Video menorDesvioVideo = null;
        double menorDesvio = Double.MAX_VALUE;


        for (Video v : acervo.getVideo()) {
            double custo = v.calculaCusto();
            double desvio = Math.abs(custo - mediaCusto);


            if (desvio < menorDesvio) {
                menorDesvio = desvio;
                menorDesvioVideo = v;
            }
        }

        // Exibe o resultado formatado
        String saida6 = "6: " + String.format("%.2f", mediaCusto) + ", " + (menorDesvioVideo != null ? menorDesvioVideo.geraTexto() : "Nenhum vídeo encontrado");
        imprimeTexto(saida6);
    }

    public void imprimeTexto(String texto) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("relatorio.txt", true));;
            bw.write(texto);
            bw.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

