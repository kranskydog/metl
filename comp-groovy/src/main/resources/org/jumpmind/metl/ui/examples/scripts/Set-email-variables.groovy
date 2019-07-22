// <Imports>
import org.jumpmind.util.FormatUtils;
// end

// onInit()
String email_to = "";
String subject = "";
String body = "";
// end

// onHandleMessage(inputMessage, messageTarget) 
if (!unitOfWorkBoundaryReached) {
	// only execute this if there is a normal message passed in and not just a control message
    String flowName = context.flowParameters['_flowName'];
    String envr = context.flowParameters['_agentName'];
    String host = context.flowParameters['_host'];
    subject = "Email Subject line can include where run: " + host + " - agent: " + envr;
    email_to = context.flowParameters['email_to'];   // this refers to a Flow Parameter set in the General Settings, could be a data element passed in as well

body = '''\
    <br/>
    Example Email body <h2> Some Heading </h2>
    <br/>
    Some detailed message generated by Metl Flow: $(_flowName).
''';
    body = FormatUtils.replaceTokens(body, context.getFlowParameters(), true);

    info("Email to: " + email_to);
    info("Email subject: " + subject);
    info("Email body: " + body);

	params = [email_subject:subject, email_body:body, email_to:email_to];
	forwardMessageWithParameters(params);
}
// end

