/*
MIT License

Copyright (c) 2022, Nuno Datia, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.zajudas;
/**
 * 
 *  Material didático para apoio 
 *  à unidade curricular de 
 *  Sistemas de Informação 
 *
 *	Os exemplos podem não ser completos e/ou totalmente correctos.
 *	São disponibilizados com objectivos pedagógicos e as
 *	eventuais incorrecções são alvo de discussão
 *	nas aulas.
 *
 *
 * 	Exemplo de mapeamento de uma associação entre duas entiddes, 
 *  com inserção em cascada. 
 *  Application-managed Entity Manager, com Local Transaction
 *  
 *  Exemplo de implementação de acessos a funcções.  
 *  Exemplos de @NamedStoredProcedureQuery.
 * */
import java.util.List;

import isel.sisinf.dal.v6.repo.JPAContext;
import jakarta.persistence.*;


public class App 
{
	public static void main( String[] args ) throws Exception
    {
    	
        try(JPAContext ctx= new JPAContext()) 
        {
        	
        	
        	ctx.beginTransaction();
        	
        	
        	System.out.println(ctx.rand_fx(1));
        	
        	ctx.fromCountry(1).forEach(c ->System.out.println(c));
        	
        	ctx.fromCountry2(1).forEach(c ->System.out.println(c));
        	
        	ctx.commit();
        	
        	
             
        } 
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        	throw e;
        }
        
    }
}
