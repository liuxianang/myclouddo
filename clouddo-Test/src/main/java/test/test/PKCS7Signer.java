package test.test;


import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
public final class PKCS7Signer {
    private static final String PATH_TO_KEYSTORE = "D:\\test1\\TestPrivate.jks";
    private static final String KEY_ALIAS_IN_KEYSTORE = "testalias";
    private static final String KEYSTORE_PASSWORD = "12345678";
    private static final String SIGNATUREALGO = "SHA256withRSA";
    public PKCS7Signer() {
    }
    KeyStore loadKeyStore() throws Exception {
        KeyStore keystore = KeyStore.getInstance("JKS");
        InputStream is = new FileInputStream(PATH_TO_KEYSTORE);
        keystore.load(is, KEYSTORE_PASSWORD.toCharArray());
        return keystore;
    }
    CMSSignedDataGenerator setUpProvider(final KeyStore keystore) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        Certificate[] certchain = (Certificate[]) keystore.getCertificateChain(KEY_ALIAS_IN_KEYSTORE);
        final List<Certificate> certlist = new ArrayList<Certificate>();
        for (int i = 0, length = certchain == null ? 0 : certchain.length; i < length; i++) {
            certlist.add(certchain[i]);
        }
        Store certstore = new JcaCertStore(certlist);
        Certificate cert = keystore.getCertificate(KEY_ALIAS_IN_KEYSTORE);
        ContentSigner signer = new JcaContentSignerBuilder(SIGNATUREALGO).setProvider("BC").
                build((PrivateKey) (keystore.getKey(KEY_ALIAS_IN_KEYSTORE, KEYSTORE_PASSWORD.toCharArray())));
        CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
        generator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").
                build()).build(signer, (X509Certificate) cert));
        generator.addCertificates(certstore);
        return generator;
    }
    byte[] signPkcs7(final byte[] content, final CMSSignedDataGenerator generator) throws Exception {
        CMSTypedData cmsdata = new CMSProcessableByteArray(content);
        CMSSignedData signeddata = generator.generate(cmsdata, true);
        return signeddata.getEncoded();
    }
    public static void main(String[] args) throws Exception {
      /* PKCS7Tool.getSigner("D:/test1","87654321","12345678");*/
      PKCS7Signer signer = new PKCS7Signer();
        KeyStore keyStore = signer.loadKeyStore();
        CMSSignedDataGenerator signatureGenerator = signer.setUpProvider(keyStore);
        String content = "some bytes to be signed";
        byte[] signedBytes = signer.signPkcs7(content.getBytes("UTF-8"), signatureGenerator);
        System.out.println("Signed Encoded Bytes: " + new String(Base64.encode(signedBytes)));
    }
}