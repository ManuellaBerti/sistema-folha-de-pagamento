public class Funcionario {
    // Atributos básicos comuns a todos os colaboradores
    private int registro;
    private String nome;
    private int tipo; // 1 = Padrão, 2 = Comissionado, 3 = Produção

    // Atributos específicos
    private double vendas;
    private double percentualComissao;
    private double valorPorPeca;
    private int quantidadeProduzida;

    // Constante para o salário base
    public static final double SALARIO_BASE = 2000.00;

    // Construtor para Funcionário Padrão
    public Funcionario(int registro, String nome) {
        this.registro = registro;
        this.nome = nome;
        this.tipo = 1;
    }

    // Construtor para Funcionário Comissionado
    public Funcionario(int registro, String nome, double vendas, double percentualComissao) {
        this.registro = registro;
        this.nome = nome;
        this.vendas = vendas;
        this.percentualComissao = percentualComissao;
        this.tipo = 2;
    }

    // Construtor para Funcionário de Produção
    public Funcionario(int registro, String nome, double valorPorPeca, int quantidadeProduzida) {
        this.registro = registro;
        this.nome = nome;
        this.valorPorPeca = valorPorPeca;
        this.quantidadeProduzida = quantidadeProduzida;
        this.tipo = 3;
    }

    // Método para calcular o salário final
    public double calcularSalarioFinal() {
        switch (this.tipo) {
            case 1:
                return SALARIO_BASE;
            case 2:
                double comissao = (this.vendas * this.percentualComissao) / 100.0;
                return SALARIO_BASE + comissao;
            case 3:
                double bonus = this.valorPorPeca * this.quantidadeProduzida;
                return SALARIO_BASE + bonus;
            default:
                return SALARIO_BASE;
        }
    }

// Métodos Getters
    public int getRegistro() {
        return registro;
    }

    public String getNome() {
        return nome;
    }

    public int getTipo() {
        return tipo;
    }

    public String getDescricaoTipo() {
        if (this.tipo == 1)
            return "Padrão";
        if (this.tipo == 2)
            return "Comissionado";
        return "Produção";
    }
}