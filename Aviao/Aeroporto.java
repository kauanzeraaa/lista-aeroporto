    /**
      * A classe Aeroporto representa um aeroporto.
      * Cada objeto Aeroporto possui uma sigla, uma cidade e uma lista de voos.
      * 
      * @author Gustavo Miguel Macedo Oliveira
      * @author João Victor Vedroni Pereira da Silva
      * @author Kauan Magalhães Piacente
      * @version 1.0
     */

 public class Aeroporto implements Cloneable{

    private String codigo;
    private String nome;
    private String cidade;
    private Lista<Voo> voos;

    /**
     * Cria um novo objeto Aeroporto com base nas informações fornecidas.
     *
     * @param cod codigo do aeroporto (sigla)
     * @param n o nome do aeroporto
     * @param c a cidade do aeroporto
     * @throws Exception se algum campo for nulo ou vazio
    */
    public Aeroporto(String cod, String n, String c) throws Exception{
        if(cod == "" || cod == null)
            throw new Exception("Código (sigla) nula!");
        if(n =="" || n == null)
            throw new Exception("Nome nulo!");
        if(c == "" || c == null)
            throw new Exception("Cidade nula!");        
        this.codigo = cod;
        this.nome = n;
        this.cidade = c;
        this.voos = new Lista<Voo>();
    }

    /**
     * Adiciona um voo à lista de voos do aeroporto.
     *
     * @param v o voo a ser adicionado
     * @throws Exception se ocorrer um erro ao adicionar o voo
    */
    public void addVoo(Voo v) throws Exception {
       this.voos.guardeUmItemNoFinal(v);
    }

    /**
     * Remove um voo da lista de voos do aeroporto.
     *
     * @param v o voo a ser removido
     * @throws Exception se ocorrer um erro ao remover o voo
    */ 
    public void removeVooIndicado(Voo v) throws Exception {
        this.voos.removaItemIndicado(v);;
    }

    /**
     * Retorna um objeto Voo com base no ID fornecido.
     *
     * @param id o ID do voo a ser buscado
     * @return o objeto Voo correspondente ao ID fornecido
     * @throws Exception se o código do voo for nulo ou se o voo não for encontrado
    */
     public Voo getVooById(int id) throws Exception {
        for(int index = 0; index < voos.getQtdLista(); index++) {
            Voo voo = voos.getElemento(index);
            if(voo.getCodVoo() == id)
                return voo;
        }
        throw new Exception("Código do vôo nulo");
    }
    
    /**
     * Verifica se o aeroporto possui um voo específico
     * 
     * @param v o voo a ser verificado
     * @return true se o eroporto possui o voo, false caso contrário
     * @throws Exception se ocorrer um erro durante a verificação
     */
    public boolean temVoo(Voo v) throws Exception {
        return this.voos.tem(v);
    }
    
    /**
     * Obtém a sigla do aeroporto
     * 
     * @return a sigla do aeroporto
     */
    public String getCod() {
        return this.codigo;
    }

    /**
     * Obtém o nome do aeroporto
     *
     * @return nome do aeroporto
    */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém os vôos
     * 
     * @return vôos
     */
    public Lista<Voo> getVoos() {
        return this.voos;
    }

    /**
     * Obtém a cidade do aeroporto
     * 
     * @return a cidade do aeroporto
     */
    public String getCidade() {
        return this.cidade;
    }

    /**
     * Retorna uma representação em string dos voos do aeroporto
     * 
     * @return uma string contendo os voos do aeroporto
     */
    public String getVoo() {
        return voos.toString();
    }

    /**
     * Define a sigla do aeroporto
     * 
     * @param cod a nova sigla do aeroporto
     */
    public void setCod(String cod) throws Exception {
        if(cod == "" || cod == null)
            throw new Exception("Mudança inválida");
        this.codigo = cod;
    }

    /**
     * Define a sigla do aeroporto
     * 
     * @param n a novo nome do aeroporto
     */
    public void setNome(String n) throws Exception {
        if(n == "" || n == null)
            throw new Exception("Mudança inválida");
        this.nome = n;
    }
    
    /**
     * Define a cidade do aeroporto
     * 
     * @param c a nova cidade do aeroporto
     */
    public void setCidade(String c) {
        this.cidade = c;
    }

    /**
     * Retorna uma representação em string do objeto Aeroporto
     * 
     * @return uma string contendo a sigla, o nome, a cidade e os voos do aeroporto
     */
    @Override
    public String toString() {
        String ret = "Código: "+this.codigo
        +"; Nome: "+this.nome +"; Cidade: "+this.cidade;
        if(this.voos.isVazia())
            ret+="; Não há vôos cadastrados";
        else    
            ret+="; Vôos: "+this.voos;        
            
        return ret;
    }

    /**
     * Retorna o código hash do objeto Aeroporto
     * 
     * @return o código hash do objeto
     */
    @Override
    public int hashCode() {
        int ret = 7;
        ret = ret*23 + this.nome.hashCode();
        ret = ret*23 + this.codigo.hashCode();
        ret = ret*23 + this.cidade.hashCode();
        ret = ret*23 + this.voos.hashCode();
        
        if(ret<0) ret = -ret;
        return ret;
    }

    /**
     * Verifica se o objeto atual é igual ao objeto fornecido
     * 
     * @param obj o objeto a ser comparado
     * @return true se os objetos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null) return false;

        Aeroporto aero = (Aeroporto)obj;

        if(!this.nome.equals(aero.nome)) return false;
        if(!this.codigo.equals(aero.codigo))return false;
        if(!this.cidade.equals(aero.cidade)) return false;
        if(!this.voos.equals(aero.voos)) return false;

        return true;
    }

    /**
     * Método construtor de cópia que é responsável por fazer uma cópia da classe Aeroporto
     * @param modelo objeto a ser copiado
     * @throws Exception caso o modelo não exista
    */
    public Aeroporto(Aeroporto model) throws Exception 
    {
        if(model == null) throw new Exception("Modelo inválido");

        this.codigo = model.codigo;
        this.nome = model.nome;
        this.cidade = model.cidade;
        this.voos = model.voos;
    }

    /**
     * Método clone que cria e retorna a copia de Lista
     * @return um clone da instancia
    */
    @Override
    public Object clone()
    {
        Aeroporto ret = null;
        try{
            ret = new Aeroporto(this);
        }catch(Exception e){}
        return ret;
    }    
}
