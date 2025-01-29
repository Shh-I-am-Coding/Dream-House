package com.ssafy.happy.user.repository;

import com.ssafy.happy.user.domain.AccessTokenBlackList;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenBlackListRepository extends CrudRepository<AccessTokenBlackList, String> {
}
