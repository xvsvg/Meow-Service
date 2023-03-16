rootProject.name = "xvsvg"
include("Lab2")
include("Lab2:controllers")
findProject(":Lab2:controllers")?.name = "controllers"
include("Lab2:services")
findProject(":Lab2:services")?.name = "services"
include("Lab2:dao")
findProject(":Lab2:dao")?.name = "dao"
include("Lab2:domain")
findProject(":Lab2:domain")?.name = "domain"
