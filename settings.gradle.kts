rootProject.name = "xvsvg"
include("Lab2")
include("Lab2:services")
findProject(":Lab2:services")?.name = "services"
include("Lab2:dao")
findProject(":Lab2:dao")?.name = "dao"
include("Lab2:domain")
findProject(":Lab2:domain")?.name = "domain"
include("Lab2:console")
findProject(":Lab2:console")?.name = "console"
include("Lab2:dto")
findProject(":Lab2:dto")?.name = "dto"
