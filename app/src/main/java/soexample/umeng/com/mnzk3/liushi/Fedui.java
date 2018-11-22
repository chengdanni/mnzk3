package soexample.umeng.com.mnzk3.liushi;

import soexample.umeng.com.mnzk3.mvp.persenter.BseaAcvitypersenter;

public class Fedui extends BseaAcvitypersenter<Feduipersenter> {
    @Override
    public Class<Feduipersenter> getClassAgeid() {
        return Feduipersenter.class;
    }
}
