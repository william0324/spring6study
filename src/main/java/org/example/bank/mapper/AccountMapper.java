package org.example.bank.mapper;
import org.example.bank.pojo.Account;

import java.util.List;

/**
 * 改接口实现类不需要写，是mybatis通过动态代理生成的实现类
 */
public interface AccountMapper {
    /**
     * 保存账户
     * @param account
     * @return
     */
    int insert(Account account);
    /**
     * 根据账号删除账户
     * @param actno
     * @return
     */
    int deleteByActno(String actno);
    /**
     * 修改账户
     * @param account
     * @return
     */
    int update(Account account);
    /**
     * 根据账号查询账户
     * @param actno
     * @return
     */
    Account selectByActno(String actno);
    /**
     * 获取所有账户
     * @return
     * */
    List<Account> selectAll();
}
