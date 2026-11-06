import java.util.*;

public class FAQChatbot {

    static String[] questions = {
        "what are the college timings",
        "how can i contact the college office",
        "what courses are available",
        "how can i pay college fees",
        "where is the library"
    };

    static String[] answers = {
        "College timings depend on the official schedule.",
        "Please contact the college office for assistance.",
        "Please check the official college website for courses.",
        "Please contact the college office for fee payment details.",
        "Please check the campus map for the library location."
    };

    static Set<String> vocabulary = new HashSet<>();
    static Map<String, Integer> documentFrequency = new HashMap<>();
    static List<Map<String, Double>> vectors = new ArrayList<>();

    static String[] tokenize(String text) {
        String cleaned = text.toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .trim();

        if (cleaned.isEmpty()) {
            return new String[0];
        }

        return cleaned.split("\\s+");
    }

    static void prepareModel() {
        for (String question : questions) {
            Set<String> uniqueWords =
                    new HashSet<>(Arrays.asList(tokenize(question)));

            vocabulary.addAll(uniqueWords);

            for (String word : uniqueWords) {
                documentFrequency.put(
                    word, documentFrequency.getOrDefault(word, 0) + 1
                );
            }
        }

        for (String question : questions) {
            vectors.add(createVector(question));
        }
    }

    static Map<String, Double> createVector(String text) {
        Map<String, Integer> counts = new HashMap<>();
        String[] words = tokenize(text);

        for (String word : words) {
            if (!word.isEmpty()) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
        }

        Map<String, Double> vector = new HashMap<>();

        for (String word : vocabulary) {
            int totalDocs = questions.length;
            int df = documentFrequency.getOrDefault(word, 0);

            double idf = Math.log(
                (1.0 + totalDocs) / (1.0 + df)
            ) + 1.0;

            double tf = counts.getOrDefault(word, 0) /
                    (double) Math.max(1, words.length);

            vector.put(word, tf * idf);
        }

        return vector;
    }

    static double cosineSimilarity(
            Map<String, Double> a,
            Map<String, Double> b) {

        double dot = 0, normA = 0, normB = 0;

        for (String word : vocabulary) {
            double x = a.getOrDefault(word, 0.0);
            double y = b.getOrDefault(word, 0.0);

            dot += x * y;
            normA += x * x;
            normB += y * y;
        }

        if (normA == 0 || normB == 0) {
            return 0;
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prepareModel();

        System.out.println("Welcome to College FAQ Chatbot!");
        System.out.println("Type 'exit' to stop.");

        while (true) {
            System.out.print("\nYou: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Bot: Goodbye!");
                break;
            }

            Map<String, Double> userVector = createVector(input);

            double bestScore = 0;
            int bestIndex = -1;

            for (int i = 0; i < vectors.size(); i++) {
                double score =
                        cosineSimilarity(userVector, vectors.get(i));

                if (score > bestScore) {
                    bestScore = score;
                    bestIndex = i;
                }
            }

            if (bestIndex != -1 && bestScore >= 0.25) {
                System.out.printf(
                    "Bot: %s (Similarity: %.2f)%n",
                    answers[bestIndex], bestScore
                );
            } else {
                System.out.println(
                    "Bot: Sorry, I don't know that answer yet."
                );
            }
        }

        sc.close();
    }
}
