package id.my.rizki.onlinecompiler.controller

import id.my.rizki.onlinecompiler.dto.CompilerOutput
import id.my.rizki.onlinecompiler.service.CompilerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1")
@Api(tags = ["compiler"])
class CompilerController(@Autowired var compilerService: CompilerService) : RequestHandler() {
    @PostMapping("/compile", produces = ["application/json"])
    @ApiOperation("Compile from java source code", notes = "Implementation of Single Class .java Application")
    fun compile(@ApiParam("Java files (before compiled)")@RequestParam("sourcecode") sourcecode: MultipartFile)
            : CompilerOutput{
        checkFileFormatReceived(sourcecode.originalFilename!!)
        return compilerService.doCompile(sourcecode)
    }
}