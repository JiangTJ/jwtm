-- add user
INSERT INTO `user` (`id`, `avatar`, `create_time`, `description`, `name`, `nick`, `password`, `sex`) VALUES ('1', 'http://img2.imgtn.bdimg.com/it/u=2381740074,3012066882&fm=27&gp=0.jpg', '2018-08-24 14:31:15.000000', NULL, 'admin', '管理员', '123456', '1');
-- add third_party
INSERT INTO `third_party` (`id`, `avatar`, `create_time`, `name`, `nick`, `type`, `user_id`) VALUES ('1', 'http://img2.imgtn.bdimg.com/it/u=2381740074,3012066882&fm=27&gp=0.jpg', '2018-08-31 17:20:25.000000', 'aa@bb.cc', 'mine', 'email', '1');

