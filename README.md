Program temel olarak user, product ve order nesnelerinden oluşmaktadır. Bunlar için belirli işlevleri gerçekletiren endpointler uygulamayı çalıştırdıktan sonra 
http://localhost:8080/swagger-ui/index.html adresinde listeli şekilde erişilebilmektedir. User ile alakı endpointler yeni user eklenip denenmek isterse diye eklenmiştir. 
Product ve order ile alakalı endpointlerin ise ne işe yaradıkları yanlarında yazmaktadır.

Uygulamanın çalışabilmesi için database oluşturmak gerekir. Bunun için gereken sql scriptler dosyaların içerisinde bulunmaktadır. Ek olarak bir manager ve bir customer ve birkaç 
product için sql verisi de sql scriptleri(data.sql) arasında bulunmaktadır.

Uygulamayı denerken application.properties üzerinden mysql database ile bağlantısının  sağlanması gerekmektedir. Database'e bağlantı kurarken eğer scriptte yazan database 
ismini değişmediyseniz, sadece database'e ait password ve username'i (application.properties dosyasının içinde) düzenlemeniz yeterlidir.

Son olarak http://localhost:8080/swagger-ui/index.html adresine gittiğinizde uygulamayı denemek için login endpointinden giriş yapmanız gerekmektedir. 
Eğer scriptler ile user data'sını yüklediyseniz şifre, rol ve kullanıcı isimleri şunlardır:
kullanıcı adı: mehmet --- şifre: 123 --- rol:customer

kullanıcı adı: manager --- şifre: 123 --- rol:manager

Giriş yapılmazsa veya rolün uygun olmadığı bir endpointe istek atılırsa 403 kodu dönmektedir.

