import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaPagamento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        int opcao = -1;

        System.out.println("=== SISTEMA DE FOLHA DE PAGAMENTO ===");

        while (opcao != 0) {
            System.out.println("\n--- MENU DE OPÇÕES ---");
            System.out.println("1 - Cadastrar Funcionário Padrão");
            System.out.println("2 - Cadastrar Funcionário Comissionado");
            System.out.println("3 - Cadastrar Funcionário Produção");
            System.out.println("4 - Gerar Folha de Pagamento");
            System.out.println("0 - Sair do Programa");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("[Erro] Opção inválida! Digite apenas números.");
                continue;
            }

            int registro;
            String nome;

            switch (opcao) {
                case 1:
                    System.out.println("\n[Cadastro Funcionário Padrão]");
                    registro = lerRegistroValido(scanner);
                    nome = lerNomeValido(scanner);

                    listaFuncionarios.add(new Funcionario(registro, nome));
                    System.out.println("Funcionário Padrão cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n[Cadastro Funcionário Comissionado]");
                    registro = lerRegistroValido(scanner);
                    nome = lerNomeValido(scanner);

                    double vendas = lerDoublePositivo(scanner, "Valor total de vendas no mês: ");
                    double percentual = lerDoublePositivo(scanner, "Percentual de comissão (%): ");

                    listaFuncionarios.add(new Funcionario(registro, nome, vendas, percentual));
                    System.out.println("Funcionário Comissionado cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.println("\n[Cadastro Funcionário de Produção]");
                    registro = lerRegistroValido(scanner);
                    nome = lerNomeValido(scanner);

                    double valorPeca = lerDoublePositivo(scanner, "Valor por peça (R$): ");
                    int qtdPecas = lerIntPositivo(scanner, "Quantidade de peças produzidas: ");

                    listaFuncionarios.add(new Funcionario(registro, nome, valorPeca, qtdPecas));
                    System.out.println("Funcionário de Produção cadastrado com sucesso!");
                    break;

                case 4:
                    System.out.println("\n=======================================================");
                    System.out.println("                 FOLHA DE PAGAMENTO                    ");
                    System.out.println("=======================================================");
                    if (listaFuncionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado no sistema.");
                    } else {
                        System.out.printf("%-10s | %-20s | %-15s | %-12s\n",
                                "REGISTRO", "NOME", "VÍNCULO", "SALÁRIO FINAL");
                        System.out.println("-------------------------------------------------------");

                        double totalFolha = 0;

                        for (Funcionario f : listaFuncionarios) {
                            System.out.printf("%-10d | %-20s | %-15s | R$ %,.2f\n",
                                    f.getRegistro(), f.getNome(),
                                    f.getDescricaoTipo(), f.calcularSalarioFinal());
                            totalFolha += f.calcularSalarioFinal();
                        }

                        System.out.println("=======================================================");
                        System.out.printf("Total de colaboradores : %d\n", listaFuncionarios.size());
                        System.out.printf("Total geral da folha   : R$ %,.2f\n", totalFolha);
                    }
                    System.out.println("=======================================================");
                    break;

                case 0:
                    System.out.println("\nSistema encerrado. Até logo!");
                    break;

                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

    private static int lerRegistroValido(Scanner sc) {
        while (true) {
            System.out.print("Número de registro: ");
            try {
                int reg = sc.nextInt();
                sc.nextLine();
                if (reg >= 0) return reg;
                System.out.println("[Erro] O número de registro não pode ser negativo.");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[Erro] Digite apenas números inteiros.");
            }
        }
    }

    private static double lerDoublePositivo(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = sc.nextDouble();
                sc.nextLine();
                if (valor >= 0) return valor;
                System.out.println("[Erro] O valor não pode ser negativo. Digite novamente.");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[Erro] Valor inválido. Digite um número.");
            }
        }
    }

    private static int lerIntPositivo(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                if (valor >= 0) return valor;
                System.out.println("[Erro] A quantidade não pode ser negativa. Digite novamente.");
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[Erro] Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static String lerNomeValido(Scanner sc) {
        while (true) {
            System.out.print("Nome completo: ");
            String nome = sc.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("[Erro] O nome não pode ser vazio.");
            } else if (!nome.matches("[a-zA-Z\u00C0-\u00FF ]+")) {
                System.out.println("[Erro] O nome deve conter apenas letras.");
            } else {
                return nome;
            }
        }
    }
}