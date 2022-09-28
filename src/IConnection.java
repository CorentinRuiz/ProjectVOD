public interface IConnection {
    boolean signIn(String mail,String pwd);

    IVODService login(String mail,String pwd);
}
