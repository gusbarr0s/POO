import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//INSTAGRAM  -- GUSTAVO DE BARROS BORBA
//Oi prof eu tive um pocuo de dificuldade no comeco para desenvolver a lógica ai
// usei um pouco do auxilio do copilot para me ajudar desenvolver a lógica do codigo

// Classe que representa um Post do usuário
class Post {
    private String conteudo;

    public Post(String content) {
        this.conteudo = content;
    }

    public String getContent() {
        return conteudo;
    }
}

// Classe que representa um Usuário
class User {
    private String usuario;
    private List<Post> posts;
    private List<User> seguindo;

    public User(String usuario) {
        this.usuario = usuario;
        this.posts = new ArrayList<>();
        this.seguindo = new ArrayList<>();
    }

    public String getUsername() {
        return usuario;
    }

    public void postar(String content) {
        posts.add(new Post(content));
    }

    public void followUser(User user) {
        if (!seguindo.contains(user)) {
            seguindo.add(user);
            System.out.println("Agora você está seguindo: " + user.getUsername());
        } else {
            System.out.println("Você já está seguindo esse usuário.");
        }
    }

    public void displayPosts() {
        if (posts.isEmpty()) {
            System.out.println("Nenhum post encontrado.");
        } else {
            System.out.println(usuario + " - Seus posts:");
            for (Post post : posts) {
                System.out.println(post.getContent());
            }
        }
    }

    public void displayFollowing() {
        if (seguindo.isEmpty()) {
            System.out.println("Você não está seguindo ninguém.");
        } else {
            System.out.println("Seguindo:");
            for (User user : seguindo) {
                System.out.println(user.getUsername());
            }
        }
    }
}

// Classe que gerencia o aplicativo Instagram
public class InstagramApp {
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Instagram ---");
            System.out.println("1. Criar usuário");
            System.out.println("2. Criar post");
            System.out.println("3. Seguir usuário");
            System.out.println("4. Ver posts");
            System.out.println("5. Ver quem estou seguindo");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    postar();
                    break;
                case 3:
                    seguir();
                    break;
                case 4:
                    displayPosts();
                    break;
                case 5:
                    displayFollowing();
                    break;
                case 6:
                    System.out.println("Encerrando o aplicativo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void createUser() {
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();
        users.add(new User(username));
        System.out.println("Usuário " + username + " criado com sucesso!");
    }

    private static User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private static void postar() {
        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();
        User user = findUser(username);

        if (user != null) {
            System.out.print("Digite o conteúdo do post: ");
            String content = scanner.nextLine();
            user.postar(content);
            System.out.println("Post criado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void seguir() {
        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();
        User user = findUser(username);

        if (user != null) {
            System.out.print("Digite o nome de usuário que deseja seguir: ");
            String toFollow = scanner.nextLine();
            User userToFollow = findUser(toFollow);

            if (userToFollow != null) {
                user.followUser(userToFollow);
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void displayPosts() {
        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();
        User user = findUser(username);

        if (user != null) {
            user.displayPosts();
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void displayFollowing() {
        System.out.print("Digite seu nome de usuário: ");
        String username = scanner.nextLine();
        User user = findUser(username);

        if (user != null) {
            user.displayFollowing();
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}
