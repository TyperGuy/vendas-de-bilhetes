/* ************************************************************************************************** */
/*  UCAN: Universidade Catolica de Angola                                                             */
/*  Ficheiro : Analise.java                                                                           */
/*  Autor: Edson Paulo Gregório                                                                       */
/*  Numero: 20830                                                                                    */
/*  Tutor: Osvaldo Ramos                                                                              */
/*  Descricao: Analise do Projecto.                                                                   */
/*  Tema: Sistema de Venda de materias de Construção                        */
/*  Data: 02.07.2021                                                                                  */
/* ************************************************************************************************** */
/*

ANALISE DO PROJECTO

Este é o projecto final da cadeira de FPII que será implementado em Java usando Swing e 
ficheiros .dat e .tab para arquivar os dados.

1-Definicao:
    Sistema de Venda de materias de Construção 
	
1.1- Objectivo:
	Pretende-se desenvolver um sistema de venda de material de construção
        para uma empresa que possui varias filiais e varios clientes. 
	 
2. GUI
    Login
    Home 
    Funcionario_GUI
    Cliente_GUI
    Material_GUI
    NotaFiscal_GUI
    Visualizar
    Pesquisa
	
Menu principal

        Ficheiro:
                - Sair
        Funcionario:
                -Novo Funcionario
                -Visualizar Funcionario
        Cliente:
                -Novo Cliente
                -Visualizar Cliente
        Material:
                -Novo Material
                -Visualizar Material
        Nota Fiscal:
                -Nova Nota Fiscal
                -Visualizar Nota Fiscal
        Tabelas:
                -Provincia
                -Municipio
                -Comuna
                -Loja
                -TipoCliente 
                -EstCivil
                -Nacionalidade
                -ValorIVA
        Pesquisar:
		-Pesquisar Funcionarios por loja
                -Pesquisar Funcionarios por sexo
        Listagem:
                -Exame:
                        -Defesa
	Ajuda
		     

2.1. Identificacao das Entidade e Tabelas Persistentes 
	
        Tabelas
		fileProvincias.tab      - Tabela2
                fileLojas.tab           - Tabela2
                fileTipoCliente.tab     - Tabela2
		fileMunicipios.tab      - Tabela3_2
                fileComunas.tab         - Tabela3_3
		fileEstCivil.tab        - Tabela2
                fileNacionalidade.tab   - Tabela2  
                fileValorIva.tab        - Tabela2  

	Ficheiros de Dados
                -Funcionarios.dat  
                -Clientes.dat          
		-Materiais.dat
                -NotasFiscais.dat
			
        ---------------------------------- Modelos -----------------------------------

        #Entidades

                Funcionario_Modelo:  
                        - codFuncionario          : inteiro 4         
                        - nome                    : string [30]
                        - telefone                : string [15] 
                        - bi                      : string [15]
                        - estadoCivil             : string [15]
                        - dataNascimento          : string [15]
                        - sexo                    : string [10]
                        - nacionalidade           : string [15]
                        - localTrabalho           : string [20]
                                139 bytes
                                sizeOf() = 139 * 2 - 4
        
                Cliente_Modelo:  
                        - codCliente              : inteiro 4         
                        - nome                    : string [30]
                        - telefone                : string [15] 
                        - provincia               : string [15]
                        - municipio               : string [15]
                        - comuna                  : string [15]
                        - tipoCliente             : string [15] 
                                109 bytes
                                sizeOf() = 109 * 2 - 4
                
                Material_Modelo:  
                        - codMaterial            : inteiro 4         
                        - nome                   : string [30]	
                        - valorUnitario          : float 4
                        - IVA                    : float 4
                                42 bytes
                                sizeOf() = 42 * 2 - 12

                NotaFiscal_Modelo:
                        - codNotaFiscal                 : inteiro 4
                        - cliente                       : string [30] -> Clientes.dat
                        - funcionario                   : string [30] -> Funcionarios.dat  
                        - loja                          : string [20] 
                        - material                      : string [30] -> Materiais.dat 
                        - valorUnitario                 : float 4 -> Materiais.dat 
                        - quantidade                    : inteiro 4 
                        - valorTotal                    : float 4 
                        - dataEmissao                   : string [15] 
                                141 bytes
                                sizeOf() = 141 * 2 - 16

6. Diversos
        - Linguagem de Programacao: Java
        - Data de Entrega e Defesa: 02/12/2019
        - Orientador: Osvaldo Ramos
*/