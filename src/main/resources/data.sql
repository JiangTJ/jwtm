-- add user
INSERT INTO `user` (`id`, `create_time`, `description`, `name`, `nick`, `password`, `sex`, `update_time`) VALUES ('1', '2018-08-24 14:31:15.000000', NULL, 'admin', '管理员', '123456', '1', '2018-08-24 14:31:12.000000');
-- add third_party
INSERT INTO `third_party` (`id`, `create_time`, `type`, `update_time`, `user_id`, `value`) VALUES ('1', '2018-08-24 23:46:27.000000', 'email', '2018-08-24 23:46:30.000000', '1', 'aa@bb.cc');

