package app;

import Cripto.Criptografias;
import dao.*;
import modelo.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

// Escolha a opcao de cadastro primeiro

        int opcao;
        do {
            exibirMenu();
            System.out.print("Digite sua opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;
                case 2:
                    realizarCadastro();
                    break;
                case 3:
                    System.out.println("Adeus!");
                    break;
                default:
                    System.out.println("Digite uma opção válida.");
            }

        } while (opcao != 3);

        scanner.close();
    }

    // os 3 menus abrem de forma diferente dependendo da sua escolha
    private static void exibirMenu() {
        System.out.println("\n===== Menu do Usuário =====");
        System.out.println("1 - Logar");
        System.out.println("2 - Cadastrar");
        System.out.println("3 - Sair");
        System.out.println("===========================");
    }

    private static void exibirMenuLogado() {
        System.out.println("\n===== Bem-vindo =====");
        System.out.println("1 - Exames");
        System.out.println("2 - Voltar");
        System.out.println("===========================");
    }

    private static void exibirMenuExame() {
        System.out.println("\n===== Menu Exame =====");
        System.out.println("1 - Gerar Resultados do Exame");
        System.out.println("2 - Ver Exame");
        System.out.println("3 - Voltar");
        System.out.println("===========================");
    }

    // apos efetuar o cadastro escolha a opcao de login
    private static void realizarLogin() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("===== Login =====");
            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            String loginHash = Criptografias.hashSha128(login);
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = usuarioDao.obterUsuarioPorLogin(loginHash);

            if (usuario != null && Criptografias.validarSenha(senha, usuario.getSenha())) {
                System.out.println("Login e senha conferem!");
                int opcao2;
                do {
                    exibirMenuLogado();
                    System.out.print("Escolha uma opção: ");
                    opcao2 = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao2) {
                        case 1:
                            exibirExame();
                            break;
                        case 2:
                            System.out.println("Adeus!");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }

                } while (opcao2 != 2);

            } else {
                System.out.println("Usuário ou senha incorretos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void realizarCadastro() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("===== Cadastro =====");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            System.out.println("Seu login sera o seu CPF!");
            String cpfCripto = Criptografias.hashSha128(cpf);

            System.out.println("Senha: ");
            String senha = scanner.nextLine();
            String senhaCript = Criptografias.hashSha128(senha);
            // Os dados sao salvos no banco ja criptografados em HASH SHA 128.

            Usuario usuarioNovo = new Usuario(cpfCripto, senhaCript);
            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.adicionarUsuario(usuarioNovo);
            Paciente pacienteNovo = new Paciente(cpf, nome);
            PacienteDao pacienteDao = new PacienteDao();
            pacienteDao.adicionarPaciente(pacienteNovo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void exibirExame() {
        Scanner scanner = new Scanner(System.in);

        int opcao3;
        do {
            exibirMenuExame();
            System.out.print("Escolha uma opção: ");
            opcao3 = scanner.nextInt();
            scanner.nextLine();

            switch (opcao3) {
                case 1:
                    gerarExame();
                    break;
                case 2:
                    verExame();
                    break;
                case 3:
                    System.out.println("Saindo... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao3 != 3);

    }

    private static void gerarExame() {
        try {
            // essa e a chave usada para abrir o resultado do exame
            String userSecretKey = Criptografias.gerarChaveSecreta();
            System.out.println("Sua chave para abrir o exame: " + userSecretKey);
            SenhaDao senhadao = new SenhaDao();
            Senha senha1 = new Senha(userSecretKey);
            senhadao.adicionarSenha(senha1);
// aqui e o Resultado do exame que vai ser criptografado
            double resultadoDoExame = 19;

            String resultadoCripto = Criptografias.encrypt(Double.toString(resultadoDoExame), userSecretKey);

            adicionarResultadoCriptoBanco(resultadoCripto);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void adicionarResultadoCriptoBanco(String resultadoCriptografado) {
        try {
            HemoglobinaDao hemoglobinaDao = new HemoglobinaDao();
            Hemoglobina hemoglobina = new Hemoglobina(resultadoCriptografado);
            hemoglobinaDao.adicionarHemoglobina(hemoglobina);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static void verExame() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("\n===== Exame =====");
            System.out.print("Informe a Chave Secreta: ");
            String userSecretKey1 = scanner.nextLine();
            obterResultadoEPrintar(1, userSecretKey1);
//aqui esta a confirmacao que o id do exame bate com a senhaSecreta que foi gerada
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void obterResultadoEPrintar(int id, String userSecretKey) {
        // esse e o metodo que faz o print final
        try {
            HemoglobinaDao hemoglobinaDao = new HemoglobinaDao();
            Hemoglobina hemoglobina = hemoglobinaDao.obterHemoglobinaPorId(id);

            if (hemoglobina != null) {
                System.out.println("\n ID: " + hemoglobina.getId());
                String resultadoDescripto = Criptografias.decrypt(hemoglobina.getResultado(), userSecretKey);

                try {
                    ValorPadraoDao vpDao = new ValorPadraoDao();
                    List<ValorPadrao> vps = vpDao.obterTodosValoresPadroes();


                    for (ValorPadrao vp : vps) {
                        System.out.println("----------------------------");
                        System.out.println("Estado: " + vp.getEstado());
                        System.out.println("----------------------------");
                        System.out.println("Max: " + vp.getLimiteMax());
                        System.out.println("----------------------------");
                        System.out.println("Min: " + vp.getLimiteMin());
                        System.out.println("----------------------------");
                        System.out.println("Unidade: " + vp.getUnidade());
                        System.out.println("----------------------------\n");


                        double resultadoNum = Double.parseDouble(resultadoDescripto);
                        System.out.println("\nSua taxa de Hemoglobina = " + resultadoDescripto + " " + vp.getUnidade());

                        System.out.println("----------------------------");
                        if (resultadoNum > vp.getLimiteMax()) {
                            System.out.println("\u001B[31mResultado acima do limite máximo.\u001B[0m");
                        } else if (resultadoNum < vp.getLimiteMin()) {
                            System.out.println("\u001B[31mResultado abaixo do limite mínimo.\u001B[0m");
                        } else {
                            System.out.println("\u001B[32mResultado dentro dos limites normais.\u001B[0m");
                        }

                        System.out.println("----------------------------");
                    }

                } catch (Exception e) {
                    System.out.println("Erro desconhecido durante a descriptografia.");
                    e.printStackTrace();
                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}









