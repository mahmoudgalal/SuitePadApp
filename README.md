# SuitePadApp
A simple implementation for the SuitePad task
<br/>

The Three apps are implemeneted as following:<br/>
<br/><b>App:</b>represents the Presentation component as described in the task
<br/><b>DataSourceApp:</b>represents the the Data source Component.(implemented as ContentProvider + Sync Service)
<br/><b>HttpServerApp</b>:represents the the HTTP Proxy Server Component.(Implemented as a long Running service and server running on port 8765)

<br/><b>ThirdPartyLibs</b>:
- NanoHttpD: is used to create a small local server on the device

<br/><b>Running the app</b><br/>
You should run/install the HttpServerApp and DataSourceApp before running the main app

<br/><b>SSL Support:<br/></b>
Firing Secure Https requests to the sever can be supported easily thanks to "NanoHttpd" built-in support for https but that requires a verified/signed certficate to be installed on the device and used by the server to secure the connections 
<br/><br/><br/>
<img src="http://s.pictub.club/2017/06/24/7we1QM.png" alt="7we1QM.png" >
