package com.bstu.cloudserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Документация MyCloud APIv3",
                description = "" +
                        "Описание основных запросов к облачному файловому хранилищу.",
                contact = @Contact(
                        name = "Alex",
                        url = "https://github.com/alex-rudenkiy",
                        email = "alex-rudenkiy@mail.ru"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://opensource.org/licenses/MIT")),
        servers = @Server(url = "http://localhost:8181")
)
class OpenAPIConfiguration {
}
