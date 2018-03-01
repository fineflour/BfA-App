package org.bfa.bfa_app;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;

public class PdfviewActivity extends Activity
        implements OnPageChangeListener, OnLoadCompleteListener {

    String strUrl;
    private static final String TAG = MainActivity.class.getSimpleName();
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        //webview = (WebView)findViewById(R.id.pdfview);
        //ProgressBar progressbar = (ProgressBar) findViewById(R.id.progressbar);

        strUrl = getIntent().getExtras().getString("url");
        String strTitle = getIntent().getExtras().getString("title");
        pdfFileName = getIntent().getExtras().getString("filename");
        pdfView = (PDFView) findViewById(R.id.pdfView);
        TextView txtPdfTitle = (TextView)findViewById(R.id.txtPdfTitle) ;
        txtPdfTitle.setText(pdfFileName);


        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator +
                pdfFileName);

        displayFromAsset(file);
    }



    private void displayFromAsset(File assetFileName) {
       // pdfFileName = assetFileName;

        pdfView.fromFile(assetFileName)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange((OnPageChangeListener) this)
                .enableAnnotationRendering(true)
                .onLoad((OnLoadCompleteListener) this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}
