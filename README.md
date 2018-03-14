# MVP_demo
在Android中，传统的使用方法是用MVC，但是我们可以发现有这很明显的弊端，在Activity或者Fragment中，直接承接了View与Presenter的重用，导致一个类下来有几百行甚至上千行代码，MVP正是解决了这个问题，将视图View与业务逻辑Presenter进行分层，每个负责各自的任务。
MVP的核心思想就是，把Activity中的UI逻辑抽象成View接口，把业务逻辑抽象成Presenter接口，Model类还是原来的Model。


image.png

下面我们通过一个简单的登录demo，来看一下MVP的使用。


image.png

这是整个代码结构，首先我们创建BaseView接口，定义UI所需要的方法，这里我们只要弹出一个Toast，以及登录成功失败的提醒就可以了，具体代码如下。
    void showToast(String msg);
    void loginSuccess();
    void loginFailue();
}
接下来，创建一个MainPresenter接口用来出来逻辑业务，

我们在MainActivity实现这个BaseView的接口，实现其方法

public interface MainPresenter {
    void attachView(BaseView v);
    void detachView();
    void login(User user);
}
创建MainPresenter的实现类MainPresenterImpl处理具体的逻辑，看MVP的关系图不难发现，P层和V层是有一个关联的，所以我们在实现类中需要绑定View视图，代码如下：

public class MainPresenterImpl implements MainPresenter {
    private BaseView mBaseView;

    @Override
    public void attachView(BaseView v) {
        this.mBaseView = v;
    }

    @Override
    public void detachView() {
        this.mBaseView = null;
    }

    @Override
    public void login(User user) {
        if(user!=null){
            if(user.getUsername().equals("fff")&&user.getPassword().equals("111111")){
                mBaseView.loginSuccess();
            }else{
                mBaseView.loginFailue();
            }
        }

    }
}
最后，我们在MainActivity也就是V层中，进行关系的关联，引入P层，做逻辑的具体实现

public class MainActivity extends AppCompatActivity implements BaseView {
    private MainPresenterImpl presenter;

    private EditText username;
    private EditText password;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl();
        presenter.attachView(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(username.getText().toString(),password.getText().toString());
                presenter.login(user);
            }
        });
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
    }

    @Override
    public void loginFailue() {
        showToast("登录失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
