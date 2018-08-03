package TKoin;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
	
	public PrivateKey privateKey;
	public PublicKey publicKey;
	
	public Wallet() {
		generateKeyPair();
	}
	public void generateKeyPair() {
		try{
			KeyPairGenerator keyGen=KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random=SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec=new ECGenParameterSpec("prime192v1");
			
			keyGen.initialize(ecSpec,random);
			KeyPair keyPair=keyGen.generateKeyPair();
			
			privateKey=keyPair.getPrivate();
			publicKey=keyPair.getPublic();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
