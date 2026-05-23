package CA_2; // Define o pacote — todos os ficheiros do projecto partilham este nome

import java.util.List; // Importa a interface List para usar listas de objectos

public class Sort { // Declara a classe Sort — contém os dois algoritmos de ordenação

    // ================================================================
    //  SECTION 1 — RECURSIVE SELECTION SORT
    // ================================================================

    // Método PÚBLICO — é este que o Main.java chama quando o utilizador escolhe Selection Sort
    public static void recursiveSelectionSort(List<Employee> employees) {
        selectionSort(employees, 0); // Inicia a recursão a partir do índice 0
    }

    // Método PRIVADO — faz o trabalho real da ordenação recursiva
    // startIndex = posição actual que estamos a preencher com o mínimo
    private static void selectionSort(List<Employee> employees, int startIndex) {

        // BASE CASE: se startIndex chegou ao último elemento, a lista está ordenada → para
        // size()-1 porque o último elemento não tem nada para comparar
        if (startIndex >= employees.size() - 1) {
            return; // return em void = "sai deste método, não faças mais nada"
        }

        // Assume que o mínimo está na posição startIndex (igual ao pseudocódigo: minIndex = i)
        int minIndex = startIndex;

        // Loop interno: percorre todos os elementos DEPOIS de startIndex
        // (igual ao pseudocódigo do professor: FOR j = i+1 to n-1)
        for (int j = startIndex + 1; j < employees.size(); j++) {

            // Obtém o nome completo na posição j em minúsculas (para comparação justa)
            String current = employees.get(j).getFullName().toLowerCase();

            // Obtém o nome completo na posição do mínimo actual em minúsculas
            String minimum = employees.get(minIndex).getFullName().toLowerCase();

            // compareTo() compara alfabeticamente:
            // resultado negativo = current vem antes de minimum → current é o novo mínimo
            if (current.compareTo(minimum) < 0) {
                minIndex = j; // actualiza a posição do mínimo
            }
        }

        // SWAP: só troca se o mínimo não está já na posição correcta
        // (evita troca desnecessária quando o elemento já está no lugar certo)
        if (minIndex != startIndex) {

            // Guarda o elemento de startIndex temporariamente para não o perder
            Employee temp = employees.get(startIndex);

            // Coloca o elemento mínimo em startIndex
            employees.set(startIndex, employees.get(minIndex));

            // Coloca o antigo elemento de startIndex onde estava o mínimo
            employees.set(minIndex, temp);
        }

        // CHAMADA RECURSIVA: avança para a próxima posição (substitui i++ do FOR do professor)
        // O elemento em startIndex já está correcto — ordena o resto
        selectionSort(employees, startIndex + 1);
    }

    // ================================================================
    //  SECTION 2 — RECURSIVE QUICK SORT
    // ================================================================

    // Método PÚBLICO — é este que o Main.java chama quando o utilizador escolhe Quick Sort
    public static void recursiveQuickSort(List<Employee> employees) {
        // Inicia com o intervalo completo: do índice 0 ao último índice
        quickSort(employees, 0, employees.size() - 1);
    }

    // Método PRIVADO — divide e ordena recursivamente em torno do pivot
    // low = início do intervalo actual, high = fim do intervalo actual
    private static void quickSort(List<Employee> employees, int low, int high) {

        // BASE CASE: secção tem 1 ou 0 elementos → já está ordenada, nada a fazer
        if (low >= high) {
            return;
        }

        // PARTITION: reorganiza os elementos em torno do pivot
        // Devolve o índice final onde o pivot foi colocado
        int pivotIndex = partition(employees, low, high);

        // CHAMADA RECURSIVA ESQUERDA: ordena tudo ANTES do pivot
        quickSort(employees, low, pivotIndex - 1);

        // CHAMADA RECURSIVA DIREITA: ordena tudo DEPOIS do pivot
        quickSort(employees, pivotIndex + 1, high);
    }

    // Método PRIVADO — o coração do Quick Sort
    // Escolhe o último elemento como pivot e reorganiza a secção:
    // elementos menores que pivot ficam à ESQUERDA
    // elementos maiores que pivot ficam à DIREITA
    // pivot fica na sua posição final correcta
    private static int partition(List<Employee> employees, int low, int high) {

        // Escolhe o ÚLTIMO elemento da secção como pivot
        String pivot = employees.get(high).getFullName().toLowerCase();

        // i é a fronteira entre "menores que pivot" e "maiores que pivot"
        // começa antes do início da secção (lado "menor" ainda vazio)
        int i = low - 1;

        // Percorre todos os elementos da secção EXCEPTO o pivot (que está em high)
        for (int j = low; j < high; j++) {

            // Obtém o nome do elemento actual
            String current = employees.get(j).getFullName().toLowerCase();

            // Se o elemento actual é MENOR que o pivot → pertence ao lado esquerdo
            // compareTo < 0 significa que current vem antes de pivot alfabeticamente
            if (current.compareTo(pivot) < 0) {

                // Expande o lado esquerdo uma posição
                i++;

                // Troca o elemento actual para o lado esquerdo
                Employee temp = employees.get(i);  // guarda o elemento na fronteira
                employees.set(i, employees.get(j)); // move o actual para a fronteira
                employees.set(j, temp);              // move a antiga fronteira para j
            }
            // Se current > pivot → não faz nada, fica no lado direito
        }

        // Coloca o pivot na sua posição final correcta
        // pivot pertence a i+1 (logo após todos os elementos menores)
        int pivotFinalIndex = i + 1;

        // Troca o pivot (actualmente em high) para a sua posição correcta (i+1)
        Employee temp = employees.get(pivotFinalIndex);
        employees.set(pivotFinalIndex, employees.get(high)); // pivot vai para posição final
        employees.set(high, temp);                           // antigo elemento vai para high

        // Devolve o índice final do pivot para o quickSort() saber onde dividir
        return pivotFinalIndex;
    }

    // ================================================================
    //  DISPLAY METHOD — partilhado pelos dois algoritmos de ordenação
    // ================================================================

    // Método PÚBLICO — mostra os primeiros 20 nomes da lista ordenada no terminal
    public static void displayFirst20(List<Employee> employees) {

        // Imprime o cabeçalho da tabela
        System.out.println("\n========= Sorted Employee List (First 20) =========");

        // Printf formata colunas com largura fixa:
        // %-4s = alinhado à esquerda, 4 caracteres
        // %-22s = alinhado à esquerda, 22 caracteres
        System.out.printf("%-4s %-22s %-25s %-20s%n",
                "#", "Full Name", "Job Title", "Department");

        // Imprime linha divisória de 75 traços
        System.out.println("-".repeat(75));

        // Math.min() garante que não ultrapassa o tamanho da lista se tiver menos de 20
        int limit = Math.min(20, employees.size());

        // Percorre os primeiros 20 (ou menos) funcionários e imprime cada linha
        for (int i = 0; i < limit; i++) {
            Employee e = employees.get(i); // obtém o funcionário na posição i

            // Imprime linha: número, nome completo, cargo, departamento
            // (i+1) porque a numeração começa em 1, não em 0
            System.out.printf("%-4d %-22s %-25s %-20s%n",
                    (i + 1),
                    e.getFullName(),
                    e.getJobTitle(),
                    e.getDepartment());
        }

        // Imprime divisória final e resumo
        System.out.println("-".repeat(75));
        System.out.println("Showing " + limit + " of " + employees.size() + " employees.");
    }
}