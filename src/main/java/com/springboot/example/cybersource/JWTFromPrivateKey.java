package com.springboot.example.cybersource;


import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;

public class JWTFromPrivateKey {

	
	 public static void main(String[] args) throws Exception {
	       
	        String pem = new String("-----BEGIN PRIVATE KEY-----\r\n"
	        		+ "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCnWrXNAs8trJEi\r\n"
	        		+ "7QUH2FgozuSd2Bnghsx/Nt4iKacRqkRtVKX+I4hXYplspADuSG19+DHpCLa9GYQI\r\n"
	        		+ "/C/hJ5tfRuP9/23mf/ZQZlNsQcMpo0CPI5T0Nvu/V4sZqxWFA+Nma6V4/KoZlmut\r\n"
	        		+ "ECtmhS9fZt5Qln8yZ3B8J2vKjQSGX+WxbN7CIMR2rwic7nOYVv84GekKv27Tp8nC\r\n"
	        		+ "rGrEJom3YbR9jKhixPZTB2A78JL/jH3hfMV6qp9QF9szld9HK9sxA3v5dyUFS6Vp\r\n"
	        		+ "P6nBZ2U7wx3yR7cI3pdTWjPB0H71DFvW7tOTuHVDy46yshYTpQMPQy5/x2/AA6/3\r\n"
	        		+ "JuW4ElA3AgMBAAECggEACqntC7MyIGbumJshxu4yL+d/Cc/LVlNAlJqabPLagnWn\r\n"
	        		+ "BpBuQ1NgTLErtZB9vrE+yNgX1hGIE2Sc1TW8l0w+ykNqI7Uy+40L1POwPSWzmeOd\r\n"
	        		+ "KYHK7ATU5cxeQLR2GpO02cHiOWNgM2EyW0S8fzvdWOblI51zXXLZ6A9a/v0dXLKF\r\n"
	        		+ "HUzestKw3d6GQ2C4bS5WKUCAbjzXQeQJ4gSJLZXZfoA/13mQyC/tvUmuAMxN3Yi8\r\n"
	        		+ "VIpGmktpt538uFLNzE1GEkLIfBYqwwTxdtnxRoG8QFd+TSqe2PgIi130rlJQhZ/S\r\n"
	        		+ "RaRk15pGUsNlCg/9+/ZhjanJ/mMt9mz0d3iai3KBgQKBgQDk4WT8xshror9PYJ5z\r\n"
	        		+ "EwlHzcXYN3CVIJ69I7NFKILAKJC7rNiVr7pOqqHVmASkFLmd3dUPFkXiqdTjhYXw\r\n"
	        		+ "D2oih2BcEHpv7G1rx7+ueeJHwu6fO+p8OoLCgGIktKFtdX4U7eHlM5VDsNVps498\r\n"
	        		+ "pqMWq1KZ60fDrG7QKlTAcqgh3QKBgQC7Lw0IQOZX0AzlQcT6w6m1+CRkRjvgpJxv\r\n"
	        		+ "UJpMti6iDrWcJHqtkKsmW2+jf/q6VFmgbS5ivYbSCtDsgPj/8mtC/XJnlLeg6sla\r\n"
	        		+ "Qaitw+cHxCAaYXb52bT+yrlIKylBEqlBq9RhYSwl/Ev6+ewo4Hq4KGTU0IDgdAQF\r\n"
	        		+ "yBikiEr7IwKBgQDGo89vil36/H4ot+QMXyMYzD++zS7KXksmP8ugY+5GM26ZUwON\r\n"
	        		+ "tbtt9q0JoSu18768ggAdXek9NMPsyr9ZfydP3EoG3M5GQN0eQ3faRzHZshZC4E87\r\n"
	        		+ "jdRaVe3XiWToiKXqKCJ7N29+FgTB/fKz7jNwwzOnHglki0yJIZP4igndiQKBgQCk\r\n"
	        		+ "NL5mAykO18C4TV4T7hmvPKQQI1zb0Qw6yqbRrPBz0huXgpjsMgEygPwKnnbBhsWp\r\n"
	        		+ "ap5KmKDxGfP1xZ6qSWu3NMdr/paoX8+LkhX7eePueCsBeYb53/ZbnlOz78kdANXi\r\n"
	        		+ "em6xjqFAIXg2D5EQuvxtGWJ7Hv4v6RauS77yd23vqQKBgQCKYdifYinE8Ls8xiQL\r\n"
	        		+ "oLaEN8Dags4v9XnNEqhLYaADeIaPGzgPW1sATOTWximoeb7ap9joZzynFMHl8jZs\r\n"
	        		+ "TY8bbw44fXnwRubWjWwn8TTDvpK0IQz3SwDwCF9z3unE4VISDDHy0UReFGEowd0t\r\n"
	        		+ "sXhVywbxUeIMaCuFRTAllpZ26w==\r\n"
	        		+ "-----END PRIVATE KEY-----");
	        pem = pem.replace("-----BEGIN PRIVATE KEY-----", "")
	                 .replace("-----END PRIVATE KEY-----", "")
	                 .replaceAll("\\s", "");

	        byte[] decoded = Base64.getDecoder().decode(pem);
	        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
	        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

	        
	        JWSSigner signer = new RSASSASigner(privateKey);

	        
	        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
	                .issuer("123456_1763793531") 
	                .expirationTime(new Date(new Date().getTime() + 10 * 60 * 1000))
	                .build();

	        SignedJWT signedJWT = new SignedJWT(
	                new JWSHeader.Builder(JWSAlgorithm.RS256).type(JOSEObjectType.JWT).build(),
	                claimsSet
	        );

	    
	        signedJWT.sign(signer);

	      
	        String token = signedJWT.serialize();
	        System.out.println("JWT Token: " + token);
	    }
}
