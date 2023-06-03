/**
 * A classe Lista implementa uma lista encadeada simples.
 * Ela permite armazenar e manipular uma sequência de elementos.
 * Os elementos são armazenados em nós individuais que são encadeados um ao outro.
 * A classe fornece métodos para adicionar, remover, acessar e manipular elementos na lista.
 * Além disso, ela também oferece funcionalidades como contar elementos, inverter a lista e concatenar listas.
 *
 * @param <X> o tipo de elemento armazenado na lista
 * 
 * @author Gustavo Miguel Macedo Oliveira
 * @author João Victor Vedroni Pereira da Silva
 * @author Kauan Magalhães Piacente
 * @version 1.0
 */

import java.lang.reflect.*;

public class Lista <X> {
    private class No {

        /**
        * A classe No representa um nó da lista encadeada.
        * Cada nó contém uma informação e uma referência para o próximo nó.
        */
        private X  info;
        private No prox;

        /**
         * Construtor da classe No.
         * @param i a informação a ser armazenada no nó
         * @param p o próximo nó da lista
         */
        public No (X i, No p) {
            this.info = i;
            this.prox = p;
        }

        /**
         * Construtor da classe No.
         * @param i a informação a ser armazenada no nó
         */
        public No (X i) {
            this.info = i;
            this.prox = null;
        }

        /**
         * Retorna a informação armazenada no nó.
         * @return a informação do nó
         */
        public X getInfo () {
            return this.info;
        }

        /**
         * Retorna o próximo nó da lista.
         * @return o próximo nó
         */
        public No getProx () {
            return this.prox;
        }

        /**
         * Define a informação do nó.
         * 
         * @param i a informação a ser definida
         */
        @SuppressWarnings("unused")
        public void setInfo (X i) {
            this.info = i;
        }

        /**
         * Define o próximo nó da lista.
         * @param p o próximo nó a ser definido
         */
        public void setProx (No p) {
            this.prox = p;
        }
    } 

    private No primeiro, ultimo;

    /**
     * Construtor da classe Lista.
     * Cria uma lista vazia.
     */
    public Lista () {
        this.primeiro=this.ultimo=null;
    }

    /**
     * Faz uma cópia (clone) de um objeto do tipo X.
     *
     * @param x o objeto a ser clonado
     * @return uma cópia do objeto clonado, ou null se ocorrer algum erro
     */
    @SuppressWarnings("unchecked")
    private X meuCloneDeX (X x) {
        X ret=null;

        try {
            Class<?> classe         = x.getClass();
            Class<?>[] tipoDosParms = null;
            Method metodo           = classe.getMethod("clone",tipoDosParms);
            Object[] parms          = null;
            ret                     = (X)metodo.invoke(x,parms);
        }
        catch(NoSuchMethodException erro) {}
        catch(IllegalAccessException erro) {}
        catch(InvocationTargetException erro) {}

        return ret;
    }

    /**
     * Adiciona um elemento no início da lista.
     *
     * @param i o elemento a ser adicionado
     * @throws Exception se a informação for nula
     */
    public void guardeUmItemNoInicio (X i) throws Exception {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            
        this.primeiro = new No (inserir,this.primeiro);

        if (this.ultimo==null)
            this.ultimo=this.primeiro;
    }

    /**
     * Adiciona um elemento no final da lista.
     *
     * @param i o elemento a ser adicionado
     * @throws Exception se a informação for nula
     */
    public void guardeUmItemNoFinal (X i) throws Exception {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = (X)meuCloneDeX(i);
        else
            inserir = i;
            
        if (this.ultimo==null) {
            this.ultimo   = new No (inserir);
            this.primeiro = this.ultimo;
        }
        else {
            this.ultimo.setProx (new No (inserir));
            this.ultimo = this.ultimo.getProx();
        }
    }

    /**
     * Retorna o elemento na posição especificada da lista.
     *
     * @param i a posição do elemento a ser retornado (começando em 1)
     * @return o elemento na posição especificada
     * @throws Exception se o índice for inválido ou se a lista estiver vazia
     */
    public X getElemento(int i) throws Exception {
        if(i < 0 || i > this.getQuantidade()) throw new Exception("índice inválido");
        No ret = this.primeiro;       
        for(int a = 1; a<=i; a++) {
            ret = ret.getProx();
        }
        return ret.getInfo();
    }

    /**
     * Retorna o elemento da posição i na lista.
     *
     * @param i a posição do elemento a ser retornado
     * @return o elemento da posição i
     * @throws Exception se o índice for inválido ou se a lista estiver vazia
     */
    public X getIezimo(int i) throws Exception {
        if(i < 0) throw new Exception("indice invalido");
        int posicao = 1;
        No atual = this.primeiro;
        for(;;) {
            if(atual == null) throw new Exception("indice invalido");
            if(posicao == i) return atual.getInfo();
            posicao++;
            atual = atual.getProx();
        }
    }
    
    /**
     * Retorna a quantidade de vezes que o elemento dado existe na lista
     *
     * @param i a posição do elemento a ser retornado
     * @return o elemento da posição i
     * @throws Exception se o índice for inválido ou se a lista estiver vazia
     */
    public int getQtdX(X x) throws Exception {
        if(x == null) throw new Exception("valor nulo");
        int qtd = 0;
        for(No atual = this.primeiro; atual != null; atual = atual.getProx()){
            if(atual.getInfo() == x)
                qtd++;
        }
        return qtd;
    }

    /**
     * Retorna o tamanho da lista
     *
     * @return a quantidade de elementos existentes na lista
     */
    public int getQtdLista() {
        int qtd = 0;
        for(No atual = this.primeiro; atual != null; atual = atual.getProx()) {
            qtd++;
        }
        return qtd;
    }

    /**
     * Retorna uma lista criada invertida da outra
     *
     * @return a lista invertida
     */
    public Lista<X> novaListaInvertida() throws Exception {
        Lista<X> invertida = new Lista<X>();
        for(No atual = this.primeiro; atual != null; atual = atual.getProx()) {
            invertida.guardeUmItemNoInicio(atual.getInfo());
        }
        return invertida;
    }

    /**
     * Concatena lista invertida da outra sem criar
     */
   public void listaInvertida() throws Exception {
        Lista<X> invertida = new Lista<X>();
        for(No atual = this.primeiro; atual != null; atual = atual.getProx()) {
            invertida.guardeUmItemNoInicio(atual.getInfo());
        }
        this.primeiro = invertida.primeiro;
        this.ultimo = invertida.ultimo;
   }


    /**
     * Concatena a lista this a uma lista que vem como parametro criando novos nós
     *
     * @param lp ListaParametro
     */
   public void listaConcatenada(Lista<X> lp) throws Exception {
        for(No atual = lp.primeiro; atual != null; atual = atual.getProx())
            guardeUmItemNoFinal(atual.getInfo());
    }

    /**
     * Retorna uma nova lista com conteudo do this concatenando ao conteudo da lista parametros
     *
     * @return lista concatenada
     * @param lp ListaParametro
     */
    // 6- Retornar nova lista com conteúdo do this concatenando ao conteúdo da lista parametros
    public Lista<X> novaListaConcatenada(Lista<X> lp) throws Exception {
        Lista<X> concatenada = new Lista<X>();
        for(No atual = this.primeiro; atual != null; atual = atual.getProx()) {
            concatenada.guardeUmItemNoFinal(atual.getInfo());
        }

        for(No atualParam = lp.primeiro; atualParam != null; atualParam = atualParam.getProx()) {
            concatenada.guardeUmItemNoFinal(atualParam.getInfo());
        }
        return concatenada;
   }
    
    /**
     * Retorna lista com os elementos comuns ao this e ao parametros
     *
     * @return lista igual
     * @param lp ListaParametro
     */
    //7- Retornar lista com os elementos comuns ao this e ao parametros
    public Lista<X> elementosIguais(Lista<X> lp) throws Exception {
        Lista<X> igual = new Lista<X>();
        for(No atualP = lp.primeiro; atualP != null; atualP = atualP.getProx()){
            if(this.tem(atualP.getInfo()))
                if(!igual.tem(atualP.getInfo()))
                    igual.guardeUmItemNoFinal(atualP.getInfo());
        }
        return igual;
    }

    /**
     * Retorna o primeiro elemento da lista sem removê-lo.
     *
     * @return o primeiro elemento da lista
     * @throws Exception se a lista estiver vazia
     */
    public X recupereItemDoInicio () throws Exception {
        if (this.primeiro==null/*&&this.fim==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    /**
     * Retorna o ultimo elemento da lista sem removê-lo.
     *
     * @return o ultimo elemento da lista
     * @throws Exception se a lista estiver vazia
     */
    public X recupereItemDoFinal () throws Exception {
        if (this.primeiro==null/*&&this.ultimo==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    /**
     * Remove o primeiro elemento da lista
     *
     * @throws Exception se a lista estiver vazia
     */
    public void removaItemDoInicio () throws Exception {
        if (this.primeiro==null /*&& this.ultimo==null*/)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) {
            this.primeiro=this.ultimo=null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
    }
    
    /**
     * Remove o ultimo elemento da lista
     *
     * @throws Exception se a lista estiver vazia
     */
    public void removaItemDoFinal () throws Exception {
        if (this.primeiro==null/*&&this.ultimo==null*/)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) {
            this.primeiro=this.ultimo=null;
            return;
        }

        No atual;
        for (atual=this.primeiro;
             atual.getProx()!=this.ultimo;
             atual=atual.getProx())
             /*comando vazio*/;

        atual.setProx(null);
        this .ultimo=atual;
    }
    
    /**
     * Retorna o tamanho da lista
     *
     * @return a quantidade de elementos existentes na lista
     */
    public int getQuantidade () {
        No  atual=this.primeiro;
        int ret  =0;

        while (atual!=null) {
            ret++;                
            atual = atual.getProx();
        }
        
        return ret;
    }

    /**
     * Verifica se a lista contém um determinado elemento.
     *
     * @param i o elemento a ser verificado
     * @return true se o elemento estiver presente na lista, false caso contrário
     * @throws Exception se a informação for nula
     */
    public boolean tem (X i) throws Exception {
        if (i==null)
            throw new Exception ("Informacao ausente");
		
        No atual=this.primeiro;

        while (atual!=null) {
            if (i.equals(atual.getInfo()))
                return true;
                
            atual = atual.getProx();
        }
        
        return false;
	}
	
    /**
     * Remove todas as ocorrências de um elemento especificado da lista.
     *
     * @param i o elemento a ser removido
     * @throws Exception se a informação for nula ou inexistente na lista
     */
	public void removaItemIndicado (X i) throws Exception {
        if (i==null)
            throw new Exception ("Informacao ausente");

        boolean removeu=false;

        for(;;) {
            if (this.primeiro==null/*&&this.ultimo==null*/)
                break;

            if (!i.equals(this.primeiro.getInfo()))
                break;
                
            if (this.ultimo==this.primeiro)
                this.ultimo=null;

            this.primeiro=this.primeiro.getProx();

            removeu=true;
        }

        if (this.primeiro!=null/*&&this.ultimo!=null*/) {
            No atual=this.primeiro;

            forever:for(;;) {
                if (atual.getProx()==null)
                    break;

                while (i.equals(atual.getProx().getInfo())) {
                    if (this.ultimo==atual.getProx())
                        this.ultimo=atual;

                    atual.setProx(atual.getProx().getProx());

                    removeu=true;

                    if (atual==this.ultimo)
                        break forever;
                }
                atual=atual.getProx();
            }
        }

        if (!removeu)
            throw new Exception ("Informacao inexistente");
	}

    /**
     * Verifica se a lista está vazia.
     *
     * @return true se a lista estiver vazia, false caso contrário
     */
    public boolean isVazia () {
        return this.primeiro==null/*&&this.ultimo==null*/;
    }
    
    /**
     * Método obrigatório toString que retorna uma representação em string de primeiro e ultimo
     * @return uma string que representa primeiro e ultimo
     */
    public String toString () {
        String ret="[";

        No atual=this.primeiro;

        while (atual!=null) {
            ret=ret+atual.getInfo();

            if (atual!=this.ultimo)
                ret=ret+",";

            atual=atual.getProx();
        }

        return ret+"]";
    }

    /**
     * Método obrigatório equals para verificar se estes objetos são iguais aos outros
     * @param obj o objeto a ser comparado
     * @return true se os objetos são iguais, false caso contrário
     */
    public boolean equals (Object obj) {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        @SuppressWarnings("unchecked")
        Lista<X> lista = (Lista<X>)obj;

        No atualThis =this .primeiro;
        No atualLista=lista.primeiro;

        while (atualThis!=null && atualLista!=null) {
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;

            atualThis  = atualThis .getProx();
            atualLista = atualLista.getProx();
        }

        if (atualThis!=null  /* && atualLista==null */)
            return false;

        if (atualLista!=null /* && atualThis ==null */)
            return false;
        // atualThis==null && atualLista==null
        return true;
    }
    
    /**
     * Método obrigatório hashcode que retorna um valor hash para estes objetos
     * O valor hash é usado para fins de comparação e deve ser consistente
     * @return o valor hash para estes objetos
     */
    public int hashCode () {
        final int PRIMO = 13; // qualquer número primo serve
        
        int ret=666; // qualquer inteiro positivo serve

        for (No atual=this.primeiro;
             atual!=null;
             atual=atual.getProx())
             ret = PRIMO*ret + atual.getInfo().hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }
    
    /**
     * Método construtor de cópia que é responsável por fazer uma cópia da classe Lista
     * @param modelo objeto a ser copiado
     * @throws Exception caso o modelo não exista
     */
    public Lista (Lista<X> modelo) throws Exception {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        if (modelo.primeiro==null)
            return; // sai do construtor, pq this.primeiro ja é null

        this.primeiro = new No (modelo.primeiro.getInfo());

        No atualDoThis   = this  .primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while (atualDoModelo!=null) {
            atualDoThis.setProx (new No (atualDoModelo.getInfo()));
            atualDoThis   = atualDoThis  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
        }

        this.ultimo = atualDoThis;
    }

    /**
     * Método clone que cria e retorna a copia de Lista
     * @return um clone da instancia
     */
    public Object clone () {
        Lista<X> ret=null;

        try 
        {
            ret = new Lista<> (this);
        }
        catch (Exception erro) {}
        return ret;
    }
}
