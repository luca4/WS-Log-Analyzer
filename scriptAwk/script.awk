BEGIN{
	print "starting process"
	
	ipAddr=""			    #client ip address
	processFinishTime=""	#end processing server time
	method=""  			    #request method eg. GET, POST...
	request=""			    #requested resource
	protocol=""			    #used protocol
	statusCode=""			#returned status code
	objectSize=""		    #returned object size
	refererHTTPRequest=""	#address
	userAgent=""			#device client used to send request

	printf("ipAddr==processFinishTime==method==request==protocol==statusCode==objectSize==refererHTTPRequest==userAgent\n") > "sanitized.txt"
}

NF > 0{      #ignore empty lines
	gsub(/\"/,"",$0)  #delete '"' char
	gsub(/\[/,"",$0)  #delete '[' char
	gsub(/\]/,"",$0)  #delete ']' char

	ipAddr = $1
	processFinishTime = sprintf("%s_%s", $4, $5)
	method = $6
	request = $7
	protocol = $8
	statusCode = $9

	if($10 == "-")
	    objectSize = 0
	else
	    objectSize = $10

	refererHTTPRequest = $11

    #read the complete user agent
	userAgent = ""
	for(i=12; i<=NF-1; ++i)
	    userAgent = sprintf("%s %s", userAgent, $i)

	printf("%s==%s==%s==%s==%s==%s==%s==%s==%s%n", ipAddr, processFinishTime, method, request, protocol, statusCode, objectSize, refererHTTPRequest, userAgent) >> "sanitized.txt"
}

END{
	print "ending process"
}