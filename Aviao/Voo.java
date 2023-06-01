/**
 * A classe Voo permite representar voos com seus respectivos índices de aeroporto de destino
 * e códigos de voo. Também suporta a clonagem de objetos e possui métodos para comparar 
 * e exibir informações sobre os voos 
 * 
 * @author Gustavo Miguel Macedo Oliveira
 * @author João Victor Vedroni Pereira da Silva
 * @author Kauan Magalhães Piacente
 * @version 1.0
 */
public class Voo implements Cloneable{
    private String indiceAeroDist;
    private int codVoo;

    /**
     * Cria um novo objeto Voo com base nos parâmetros fornecidos.
     *
     * @param iA o índice do aeroporto de destino
     * @param cV o código do voo
     * @throws Exception se o valor do código for inválido (menor ou igual a zero)
     */
    public Voo(String iA, int cV) throws Exception{
        if(iA == "" || iA == null)
            throw new Exception("Código do aeroporto inválido");
        if(cV <= 0) 
            throw new Exception("Valores de código inválido");
        this.indiceAeroDist = iA;
        this.codVoo = cV;
    }

    /**
     * Obtém o índice do aeroporto de destino do voo.
     *
     * @return o índice do aeroporto de destino
     */
    public String getIndiceAeroDist() {
        return this.indiceAeroDist;
    }

    /**
     * Obtém o código do voo.
     *
     * @return o código do voo
     */
    public int getCodVoo() {
        return this.codVoo;
    }

    /**
     * Define o índice do aeroporto de destino do voo.
     *
     * @param iA o novo índice do aeroporto de destino
     * @throws Exception se o valor do código for inválido
     */
    public void setIndiceAeroDist(String iA) throws Exception {
        if(iA == "" || iA == null)
            throw new Exception("Código do aeroporto inválido");
        this.indiceAeroDist = iA;
    }

    /**
     * Define o código do voo.
     *
     * @param cV o novo código do voo
     * @throws Exception se o valor do código for inválido (menor ou igual a zero)
     */
    public void setCodVoo(int cV) throws Exception {
        if(cV <= 0)
            throw new Exception("Valor de código inválido");
        this.codVoo = cV;
    }

    /**
     * Método obrigatório hashcode que retorna um valor hash para estes objetos
     * O valor hash é usado para fins de comparação e deve ser consistente
     * @return o valor hash para estes objetos
     */
    @Override
    public int hashCode()
    {
        int ret = 7;
        ret = ret *23 + Integer.valueOf(this.indiceAeroDist).hashCode();
        ret = ret *23 + Integer.valueOf(this.codVoo).hashCode();

        if(ret<0) ret=-ret;
        return ret;
    }

    /**
     * Método obrigatório equals para verificar se estes objetos são iguais aos outros
     * @param obj o objeto a ser comparado
     * @return true se os objetos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(obj==null) return false;
        if(this.getClass()!=obj.getClass()) return false;
        Voo v = (Voo)obj;
        if(this.indiceAeroDist != v.indiceAeroDist) return false;
        if(this.codVoo != v.codVoo) return false;

        return true;
    }

    /**
     * Método obrigatório toString que retorna uma representação em string do indice do aeroporto e código de voô
     * @return uma string que representa indice do aeroporto e código de voô
     */
    @Override
    public String toString() {
        return "Código do aeroporto: "+this.indiceAeroDist+", Código vôo: "+this.codVoo;
    }

    /**
     * Método construtor de cópia que é responsável por fazer uma cópia da classe Voo
     * @param modelo objeto a ser copiado
     * @throws Exception caso o modelo não exista
     */
    public Voo(Voo model) throws Exception {
        if(model == null)
            throw new Exception("modelo ausente");
        this.indiceAeroDist = model.indiceAeroDist;
        this.codVoo = model.codVoo;
    }

    /**
     * Método clone que cria e retorna a copia de Voo
     * @return um clone da instancia
     */
    @Override
    public Object clone() {
        Voo ret = null;
        try {
            ret = new Voo(this);
        }catch(Exception e) {}
        return ret;
    }
}
