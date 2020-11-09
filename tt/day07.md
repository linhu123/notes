# Day07

## T1 真理尺度和价值尺度的辩证关系原理 （背诵笔记p44）
使用场景

当我们在做题的时候，读到材料中的故事有这样的一些情形：

人类为了一己私欲，不断地向自然索取，完全违背规律，为所欲为，最终自食恶果。

举个例子：我们不断地毁林造田，最终黄沙满天；我们破坏了生态平衡，最终疫情肆虐。

类似于这些故事，我们可以考虑写下这个原理。

---

背诵内容

真理尺度：在实践中人们必须遵循正确反映客观事物本质和规律的真理。

价值尺度：在实践中人们都是按照自己的尺度和需要去认识世界和改造世界，创造和实现价值。

二者的辩证关系：

首先，成功的实践必然是以真理和价值的辩证统一为前提的。

其次，价值的形成和实现必须以坚持真理为前提，而真理又必然是具有价值的。

最后，真理和价值在实践和认识活动中是相互制约、相互引导、相互促进的。

## T2  社会形态更替的特点 （背诵笔记p51-52）
使用场景

当我们做题的时候，如果读到材料中的故事，有这样的一些情形：

比如某些国家和民族，他们跳过一种或几种社会形态，跳跃式的向前发展，这类故事可以体现这个原理。

还比如有另外一些国家和民族逆历史潮流而为，有反全球化、闭关锁国、实行贸易保护、制造贸易摩擦等等行为，这些行为是违背历史规律的，他们阻止不了历史前进的脚步。

像这类故事，也可以用这个原理来进行分析。

---

背诵内容

第一，社会形态更替的过程呈现出统一性和多样性。

▽△▽△

第二，社会形态更替是必然性与人们的历史选择性共同作用的结果。

人们的历史选择性包含三层意思：

首先，社会发展的客观必然性造成了一定历史阶段社会发展的基本趋势，为人们的历史选择提供了基础、范围和可能性空间。

其次，社会形态更替的过程也是一个合目的性与合规律性相统一的过程。

最后，人们的历史选择性，社会形态更替归根到底是人民群众的选择性。

▽△▽△

第三，社会形态更替的过程呈现出前进性和曲折性。

社会发展过程中的决定性、统一性表明社会发展的总趋势是前进的；社会发展过程中的选择性、多样性表明社会发展的具体道路不是直线的，而是曲折的。



```java
/**
     * 新版本
     * @param branchId
     * @return
     */
    public MenuVo filterMenu(Integer branchId) {

        //查询参数是否配置了  菜单过滤
        SysParameter filter_menu = sysParameterDao.findSysParameter(0, "BACKGROUND_RUN");
        if(filter_menu == null){
            throw new BizException(ErrorCodes.DEFAULT_BIZ_COMMON_ERROR,"未查询到对应的系统参数");
        }
        MenuVo menuVo = new MenuVo();
        if (filter_menu.getParamValue().equals("0")){
            //直接写死的，免得查数据库
            menuVo.setFilter(0);
            UdcBaseMenu udcBaseMenu = new UdcBaseMenu();
            udcBaseMenu.setId(134);
            UdcBaseMenu udcBaseMenu1 = new UdcBaseMenu();
            udcBaseMenu1.setId(135);
            menuVo.setFilterMenus(Arrays.asList(udcBaseMenu,udcBaseMenu1));
            return menuVo;
        }
        menuVo.setFilter(Integer.parseInt(filter_menu.getParamValue()));
        //通过branchId 获取的所有的服务包
        //然后通过服务包 查到所有的菜单
        Set<UdcBaseMenu> bscSet = new HashSet<>();
        List<BgServiceRecodeExt> services = bgServiceRecodeDao.getBgServiceRecodesByBranchId(branchId).stream().filter(i ->
                i.getBack1().equals("0")
        ).filter(i -> i.getState().equals(ApplicationConst.EXPIRED_STATE))
                .collect(Collectors.toList());//当前结构开通的服务(过期的)。
        if (!services.isEmpty()) {
            for (BgServiceRecodeExt i : services) {
                List<BgMenu> bgMenus = bgMenuDao.getByServiceId(i.getServiceId());
                for (BgMenu menu : bgMenus
                ) {
                    //如果是夫菜单,其下面的子菜单都要被过滤.
                    //如果是子菜单,只过滤这一个菜单.
                    bscSet.add(getBscMenuById(menu.getMenuId()));
                    if (menu.getParentFlag() == 1) {
                        bscSet.addAll(getBscMenuByPid(menu.getMenuId()));
                    } else {
                        UdcBaseMenu bscMenu = baseSystemMenuDao.findBscMenu(menu.getMenuId());
                        bscSet.add(bscMenu);
                    }
                }
            }
        }
        List<UdcBaseMenu> result = new ArrayList<>();
        result.addAll(bscSet);
        menuVo.setFilterMenus(result);
        return menuVo;
    }

private UdcBaseMenu getBscMenuById(Integer id){
        UdcBaseMenu bscMenu = baseSystemMenuDao.findBscMenu(id);
        if (bscMenu == null){
            log.info("服务对应的父菜单id,未在bscMenu找到对应的数据");
        }
        return bscMenu;
    }


git config --global alias.lg1  "log --graph --abbrev-commit --decorate --format=format:'%C(bold blue)%h%C(reset) - %C(bold green)(%ar)%C(reset) %C(white)%s%C(reset) %C(dim white)- %an%C(reset)%C(bold yellow)%d%C(reset)' --all"
    
git config --global alias.lg2  "lg2 = log --graph --abbrev-commit --decorate --format=format:'%C(bold blue)%h%C(reset) - %C(bold cyan)%aD%C(reset) %C(bold green)(%ar)%C(reset)%C(bold yellow)%d%C(reset)%n''          %C(white)%s%C(reset) %C(dim white)- %an%C(reset)' --all"
    
 git config --global alias.lg = !"git lg1"



```

